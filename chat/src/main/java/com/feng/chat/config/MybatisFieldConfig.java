package com.feng.chat.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MybatisFieldConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置属性值
        if ( metaObject.hasSetter("createTime")) this.setFieldValByName("createTime",new Date(),metaObject);
        if ( metaObject.hasSetter("modifyTime")) this.setFieldValByName("modifyTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置属性值
        // DESC mybatis-plus的字段自动注入的方式
        if ( metaObject.hasSetter("modifyTime")) this.setFieldValByName("modifyTime",new Date(),metaObject);
    }
}