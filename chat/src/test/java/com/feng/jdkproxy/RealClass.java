package com.feng.jdkproxy;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
public class RealClass implements ProxyInterface{
    @Override
    public String SayHello(String name) {
        System.out.println("hi");
        return "Hello";
    }

    @Override
    public String SayGoodBye() {
        System.out.println("bye");
        return "滚呐";
    }
}
