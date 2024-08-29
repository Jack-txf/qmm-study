package com.feng.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
public class TestMain0 {
    public static void main(String[] args) {
        RealClass realClass = new RealClass();
        MyInvocation myInvocation = new MyInvocation(realClass);

        ClassLoader loader = realClass.getClass().getClassLoader();
        Class[] interfaces = realClass.getClass().getInterfaces();

        ProxyInterface proxyInstance = (ProxyInterface) Proxy.newProxyInstance(loader, interfaces, myInvocation);
        proxyInstance.SayGoodBye();
    }
}
