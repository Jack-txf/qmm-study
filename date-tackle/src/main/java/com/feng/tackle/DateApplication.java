package com.feng.tackle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.feng.tackle.dao")
public class DateApplication {
    public static void main(String[] args) {
        SpringApplication.run(DateApplication.class, args);
    }
}
