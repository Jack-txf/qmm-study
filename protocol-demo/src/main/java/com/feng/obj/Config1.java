package com.feng.obj;

import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/3/20 10:37
 * @注释
 */
@Component
public class Config1 {
    private String name = "config1";

    public Config1() {
        System.out.println("Config1 无参构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
