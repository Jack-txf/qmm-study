package com.feng.cglib.demo1;

/**
 * @author tong
 * @Description：测试类
 */
public class ProxyTest {


    public static void main(String[] args) {
        Star star = new Star();
        /* proxy就是我们创建的代理对象，这个对象可以执行被代理类中所有的方法，
        并且我们可以在代理对象中对被代理类的方法进行增强，
        注意这里使用了强转，因为getProxy方法的返回值是Object类型的对象*/
        Star proxy = (Star)ProxyFactory.getProxy(star);
        System.out.println("-----------------分割线------------------");
        proxy.sing();
        System.out.println("-----------------分割线------------------");
        proxy.dance();
        System.out.println("-----------------分割线------------------");
        // 创建被代理类的对象
        ChinesePeople chineseCartoon = new ChinesePeople();
        // 获取代理对象
        ChinesePeople proxy1 = (ChinesePeople)ProxyFactory.getProxy(chineseCartoon);
        proxy1.person("青莲地心火","陨落心炎");
    }
}

