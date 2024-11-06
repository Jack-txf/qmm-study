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
        registry.addMapping("/**") // 允许所有访问路径
                .allowedOrigins("*") // 允许的域名，可以使用*表示所有域名，或者指定具体的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                .allowedHeaders("*") // 允许的请求头
                // .allowCredentials(true) // 是否允许请求携带验证信息（如Cookies）
                .maxAge(3600); // 预检请求的有效期（秒）
    }
}
