package com.feng.data.config;

import lombok.Data;

@Data
public class DataSourceConfig {

    /**
     * 驱动类
     */
    protected String driverClass;

    /**
     * jdbc url
     */
    protected String jdbcUrl; //例如这样的：jdbc:mysql://localhost:3306/db_summer_1?useUnicode=true&characterEncoding=utf-8

    /**
     * 用户名
     */
    protected String user;

    /**
     * 密码
     */
    protected String password;
}