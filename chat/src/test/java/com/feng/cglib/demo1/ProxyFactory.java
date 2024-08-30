package com.feng.cglib.demo1;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 通过该方法可以生成任意目标类所对应的代理类
    public static Object getProxy(Object target) {
        // proxy就是我们创建的代理对象，这个对象可以执行被代理类中所有的方法，并且我们可以在代理对象中对被代理类的方法进行增强
        /*
        这里的create方法有两个参数，分别是Class type和Callback callback。
        其中Class type是指被代理类的字节码文件，因为有了被代理类的字节码后（即：被代理类的运行时类），就相当于可以获取被代理类的全部信息
        Callback callback是用于提供增强代码的，一般都是写一个接口的实现，通常情况下都是匿名内部类，这里我们一般不适用Callback接口，而是使用它的子接口MethodInterceptor的实现类。
         */
        Object proxy = Enhancer.create(target.getClass(), new MethodInterceptor() {
            /**
             * 这一步是整个过程的关键，代理类的实现要通过Enhancer类，我们需要通过Enhancer类中的create方法创建一个代理对象
             * @param o 是一个代理对象的引用 (即：增强对象)
             * @param method 是当前执行，即被拦截的被代理类方法
             * @param objects 是当前执行方法所用的参数，索引顺序即为方法定义时参数的顺序
             * @param methodProxy 指的是当前执行的方法的代理对象
             * @return 通过反射调用method对象所表示的方法, 并获取该方法的返回值
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
                Object result = null;
                try {
                    // 提供增强代码
                    System.out.println("[动态代理][日志] " + method.getName() + "，参数：" + Arrays.toString(objects));
                    // 通过反射调用method对象所表示的方法,并获取该方法的返回值
                    // 在具有指定参数的指定对象上调用此method对象表示的底层方法。
                    // 此处就是通过target来确定调用的是具体哪个类中的方法
                    result = method.invoke(target, objects);
                    System.out.println("[动态代理][日志] " + method.getName() + "，结 果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[动态代理][日志] " + method.getName() + "，异常：" + e.getMessage());
                } finally {
                    System.out.println("[动态代理][日志] " + method.getName() + "，方法执行完毕");
                }
                return result;
            }
        });
        // 返回代理对象
        return proxy;
    }
}