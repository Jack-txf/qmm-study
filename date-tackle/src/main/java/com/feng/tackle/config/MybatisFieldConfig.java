package com.feng.tackle.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MybatisFieldConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //设置属性值
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("modifyTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //设置属性值
        this.setFieldValByName("modifyTime",new Date(),metaObject);
    }
}
