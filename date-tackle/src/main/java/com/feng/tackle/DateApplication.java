package com.feng.tackle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Iterator;

@SpringBootApplication
@MapperScan("com.feng.tackle.dao")
@EnableScheduling
@EnableAsync
public class DateApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DateApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Iterator<String> iterator = beanFactory.getBeanNamesIterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if ( name.equals("dog01") || name.equals("cat01") ) System.out.println(name);
        }
    }
}

