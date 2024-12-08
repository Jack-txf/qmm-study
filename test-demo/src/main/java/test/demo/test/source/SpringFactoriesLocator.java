package test.demo.test.source;

import java.net.URL;
import java.util.Enumeration;

public class SpringFactoriesLocator {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader.getResources("META-INF/spring.factories");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println("Found: " + url);
        }
    }
}