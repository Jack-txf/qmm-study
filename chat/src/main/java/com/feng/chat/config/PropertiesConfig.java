package com.feng.chat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "white")
@EnableConfigurationProperties
@Data
public class PropertiesConfig {
    private List<String> urls; // 登录拦截器的白名单
}
