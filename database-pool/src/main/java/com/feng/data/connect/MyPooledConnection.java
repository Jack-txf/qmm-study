package com.feng.data.connect;

import com.feng.data.IPooledDataSource;

import java.sql.Connection;

public interface MyPooledConnection extends Connection {
    /**
     * 是否繁忙
     */
    boolean isBusy();

    /**
     * 设置是否繁忙状态
     */
    void setBusy(boolean busy);

    /**
     * 获取真正的连接
     */
    Connection getConnection();

    /**
     * 设置连接信息
     */
    void setConnection(Connection connection);

    /**
     * 设置数据源信息
     * @param dataSource
     */
    void setDataSource(final IPooledDataSource dataSource);
}
