package com.feng.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
public class TestMain0 {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        RealClass realClass = new RealClass();
        MyInvocation myInvocation = new MyInvocation(realClass);

        ClassLoader loader = realClass.getClass().getClassLoader();
        Class[] interfaces = realClass.getClass().getInterfaces();

        ProxyInterface proxyInstance = (ProxyInterface) Proxy.newProxyInstance(loader, interfaces, myInvocation);

        // class com.sun.proxy.$Proxy0~~~~~~~~~~!!!!
        /*
        （通过反射，构造方法）创造的一个$Proxy0类对象（继承Proxy 实现接口），在内存中，故如果不加以设置是不会在磁盘中看见的，
        在$Proxy0的构造方法中调用了super(h)，这样Proxy就持有了InvocationHandler实例
         */
        System.err.println(proxyInstance.getClass());

        proxyInstance.SayHello("txf");
    }
}
