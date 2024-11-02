package com.feng.chat.config;

import com.feng.chat.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private PropertiesConfig propertiesConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录拦截器
        registry.addInterceptor(loginInterceptor).order(1)
                .addPathPatterns("/**") // 拦截所有的
                .excludePathPatterns(propertiesConfig.getUrls()); // 除了白名单
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 指定哪些路径需要CORS支持
                .allowedOrigins("*") // 允许哪些源进行跨域请求
                // .allowedOriginPatterns(".*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许哪些HTTP方法
                .allowedHeaders("*"); // 允许哪些HTTP头
                // .allowCredentials(true); // 是否允许发送凭证（如Cookies）
    }
}
