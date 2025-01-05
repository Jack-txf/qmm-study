package com.feng.tackle.config.source;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;


public class MyBootstrapInit implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {
        System.out.println("【MyBootstrapInit】--------------方法执行了");
    }
}
