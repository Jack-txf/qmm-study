package com.feng.data;


import com.feng.data.config.PooledDataSourceConfig;
import com.feng.data.connect.PooledConnection;
import com.feng.data.exception.ConnPoolException;
import com.feng.data.utils.DriverUtil;
import lombok.Data;
import lombok.extern.java.Log;
import org.junit.platform.commons.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 数据源
 *
 * @author summer
 * @version : DefaultDataSource.java, v 0.1 2022年05月15日 10:19 PM summer Exp $
 */
@Log
@Data
public class DefaultDataSource extends AbstractDataSource implements IPooledDataSource, LifeCycle {

    /**
     * 数据源相关配置
     */
    private PooledDataSourceConfig dataSourceConfig;

    /**
     * 池化连接列表
     */
    private List<PooledConnection> pooledConnectionList = new ArrayList<>();

    /**
     * 初始化
     */
    public synchronized void init() {
        if (!checkConfigLegal()) {
            return;
        }
        //加载驱动类
        DriverUtil.loadDriverClass(dataSourceConfig.getDriverClass(), dataSourceConfig.getJdbcUrl());
        //连接池初始化
        this.initJdbcPool();
        //校验连接有效性 --- 单独开一个线程来检测！！！！！！！！
        initTestOnIdle();
    }

    @Override
    public synchronized PooledConnection getConnection() throws SQLException {
        log.info("getConnection begin....");
        logConnPoolDigest(pooledConnectionList);

        //1. 获取第一个不是 busy 的连接
        Optional<PooledConnection> connectionOptional = getFreeConnectionFromPool();
        if(connectionOptional.isPresent()) {
            return connectionOptional.get();
        }

        //2. 考虑是否可以扩容
        if(this.pooledConnectionList.size() >= this.dataSourceConfig.getMaxSize()) {
            //2.1 立刻返回
            if(this.dataSourceConfig.getMaxWaitMills() <= 0) {
                throw new ConnPoolException("从连接池中获取失败");
            }

            log.info("开始等待空闲连接出现...");
            try {
                wait(this.dataSourceConfig.getMaxWaitMills());

                log.info("等待结束，获取连接");
                return getConnection();
            } catch (InterruptedException exception) {
                log.info("等待异常");
                exception.printStackTrace();
                throw new SQLException("等待空闲连接异常");
            }
        } else {
            //3. 扩容（暂时只扩容一个）
            log.info("开始扩容连接池大小...");
            PooledConnection pooledConnection = createPooledConnection();
            pooledConnection.setBusy(true);
            this.pooledConnectionList.add(pooledConnection);
            log.info("扩容完成...");
            logConnPoolDigest(pooledConnectionList);

            return pooledConnection;
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    // 用完之后释放连接-----------
    @Override
    public synchronized void returnConnection(PooledConnection pooledConnection) {
        // 验证状态
        if(this.dataSourceConfig.isTestOnReturn()) {
            checkValid(pooledConnection);
        }

        // 设置为不繁忙
        log.info("释放连接...");
        pooledConnection.setBusy(false);

        logConnPoolDigest(pooledConnectionList);

        //通知其他线程
        notifyAll();
    }

    /**
     * 获取空闲的连接
     * @return 连接
     * @since 1.3.0
     */
    private Optional<PooledConnection> getFreeConnectionFromPool() {
        for(PooledConnection pc : this.pooledConnectionList) {
            if(!pc.isBusy()) {
                pc.setBusy(true);
                log.info("从连接池中获取连接");

                // 验证有效性
                if(this.dataSourceConfig.isTestOnBorrow()) {
                    checkValid(pc);
                }

                return Optional.of(pc);
            }
        }
        // 空
        return Optional.empty();
    }

    /**
     * 空闲时校验
     */
    private void initTestOnIdle() {
        if(StringUtils.isNotBlank(this.dataSourceConfig.getValidQuery())) {
            ScheduledExecutorService idleExecutor = Executors.newSingleThreadScheduledExecutor();

            idleExecutor.scheduleAtFixedRate(this::testOnIdleCheck, this.dataSourceConfig.getTestOnIdleIntervalSeconds(),
                    this.dataSourceConfig.getTestOnIdleIntervalSeconds(), TimeUnit.SECONDS);
        }
    }

    /**
     * 验证空闲连接是否有效
     */
    private void testOnIdleCheck() {
        for(PooledConnection pc : this.pooledConnectionList) { // 遍历连接数组
            if(!pc.isBusy()) { // 该连接不忙的话
                checkValid(pc);
            }
        }
    }

    /**
     * 校验连接是否成功
     * @param pooledConnection 池化连接
     */
    private void checkValid(final PooledConnection pooledConnection) {
        log.info("开始校验连接");
        if(StringUtils.isNotBlank(this.dataSourceConfig.getValidQuery())) {
            Connection connection = pooledConnection.getConnection(); // 得到持有的连接
            try {
                // 如果连接无效，重新申请一个新的替代
                if(!connection.isValid(this.dataSourceConfig.getValidTimeOutSeconds())) {
                    log.info("连接无效，创建新连接");
                    Connection newConnection = createConnection();
                    pooledConnection.setConnection(newConnection);
                    log.info("连接无效，创建新连接成功");
                }
            } catch (SQLException throwables) {
                throw new ConnPoolException(throwables);
            }
        } else {
            log.info("校验SQL为空，跳过连接校验");
        }
    }

    /**
     * 初始化连接池
     */
    private void initJdbcPool() {
        final int minSize = this.dataSourceConfig.getMinSize(); // 获取连接数最小值
        pooledConnectionList = new ArrayList<>(minSize);

        for(int i = 0; i < minSize; i++) {
            pooledConnectionList.add(createPooledConnection());
        }
    }

    /**
     * 创建一个池化的连接
     * @return
     */
    private PooledConnection createPooledConnection() {
        Connection connection = createConnection();

        PooledConnection pooledConnection = new PooledConnection();
        pooledConnection.setBusy(false);
        pooledConnection.setConnection(connection); // pooledConnection 持有DriverManager获取到的连接
        pooledConnection.setDataSource(this);
        return pooledConnection;
    }

    /**
     * 创建一个新连接
     * @return 连接
     */
    private Connection createConnection() {
        try {
            log.info("创建新连接.....");
            return DriverManager.getConnection(this.dataSourceConfig.getJdbcUrl(),
                    this.dataSourceConfig.getUser(), this.dataSourceConfig.getPassword());
        } catch (SQLException e) {
            log.warning("创建连接异常," + e.getMessage());
            throw new ConnPoolException(e);
        }
    }

    /**
     * 检查数据源配置是否合法
     */
    private boolean checkConfigLegal() {
        if (this.dataSourceConfig == null) {
            throw new ConnPoolException("数据源配置缺失");
        }

        if (StringUtils.isBlank(dataSourceConfig.getDriverClass())
                && StringUtils.isBlank(this.dataSourceConfig.getJdbcUrl())) {
            throw new ConnPoolException("数据源配置缺失");
        }

        return true;
    }

    /**
     * 日志打印
     */
    private static void logConnPoolDigest(List<PooledConnection> pooledConnectionList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pooledConnectionList status:[" + pooledConnectionList.size()).append(",busy status:[");
        for (PooledConnection pooledConnection : pooledConnectionList) {
            stringBuilder.append(pooledConnection.isBusy()).append(",");
        }
        stringBuilder.append("]]");

        log.info(stringBuilder.toString());
    }
}
