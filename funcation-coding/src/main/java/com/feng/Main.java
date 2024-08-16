package com.feng;

import com.feng.res.ResourceResolver;

import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/16
 */
public class Main {
    public static void main(String[] args) {
        ResourceResolver resolver = new ResourceResolver("com.feng");
        List<Object> list = resolver.scan(resource -> {
            return null;
        });
    }
}
