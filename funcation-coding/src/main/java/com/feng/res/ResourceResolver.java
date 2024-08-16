package com.feng.res;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/16
 */
// 资源扫描器
public class ResourceResolver {
    private String basePackage;

    public ResourceResolver(String basePackage) {
        this.basePackage = basePackage;
    }

    public <R> List<R> scan(Function<Resource, R> mapper) {
        // 文件路径
        String basePackagePath = this.basePackage.replaceAll(".", "/");
        // 包名
        String basePackage = this.basePackage;
        ArrayList<R> collector = new ArrayList<>();
        scan0(basePackage, basePackagePath, collector, mapper);
        return collector;
    }

    private <R> void scan0(String basePackage, String basePackagePath, ArrayList<R> collector, Function<Resource,R> mapper) {
        System.out.println("扫描的路径：" + basePackagePath);
        ClassLoader contextClassLoader = getContextClassLoader();
        try {
            Enumeration<URL> urlEnumeration = contextClassLoader.getResources(basePackagePath);
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                System.out.println(url);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ClassLoader getContextClassLoader() {
        ClassLoader cl = null;
        cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = getClass().getClassLoader();
        }
        return cl;
    }
}
