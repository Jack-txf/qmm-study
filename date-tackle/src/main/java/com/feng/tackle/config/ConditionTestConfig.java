package com.feng.tackle.config;

import com.feng.tackle.entity.Cat;
import com.feng.tackle.entity.Dog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionTestConfig {

    @ConditionalOnClass(name = "test.demo.test.list.Student")
    @Bean
    public Dog dog01() {
        return new Dog(2, "汪汪汪");
    }

    @ConditionalOnMissingClass("test.demo.test.list.Student")
    @Bean
    public Cat cat01() {
        return new Cat(1, "喵喵喵");
    }
}
