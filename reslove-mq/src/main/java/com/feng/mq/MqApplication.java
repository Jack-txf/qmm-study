package com.feng.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MqApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MqApplication.class, args);
        //String[] beanDefinitionNames = context.getBeanDefinitionNames();
        //for (String beanDefinitionName : beanDefinitionNames) {
        //    System.out.println(beanDefinitionName);
        //}
    }
}