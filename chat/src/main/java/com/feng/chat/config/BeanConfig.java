package com.feng.chat.config;

import com.feng.chat.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*
 组件配置类--生成需要的bean
 */
@Component
public class BeanConfig {
    @Bean("loginInterceptor")
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
