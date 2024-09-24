package com.feng.data;


import com.feng.data.connect.PooledConnection;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 池化数据源接口
 *
 * @author summer
 * @version : IPooledDataSourceConfig.java, v 0.1 2022年05月15日 10:36 PM summer Exp $
 */
public interface IPooledDataSource extends DataSource {
    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    PooledConnection getConnection() throws SQLException;
    /**
     * 归还连接
     *
     * @param pooledConnection 连接池
     */
    void returnConnection(PooledConnection pooledConnection);
}