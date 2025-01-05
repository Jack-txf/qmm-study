package com.feng.tackle.config.source;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;


public class MyApplicationContextInit implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("【MyApplicationContextInit】------------" + applicationContext.getApplicationName());
    }
}
