package com.feng.tools;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        //主线程中
        ThreadLocal<String> k1 = new ThreadLocal<>();
        ThreadLocal<Integer> k2 = new ThreadLocal<>();
        ThreadLocal<Object> k3 = new ThreadLocal<>();
        k1.set("main thread");
        k2.set(1000);
        k3.set("hallo~");

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1:"+k1.get());
            System.out.println("thread1:"+k2.get());
            System.out.println("thread1:"+k3.get());

            k1.set("123");
            k2.set(1);
            k3.set("sss");

            System.out.println("thread1:"+k1.get());
            System.out.println("thread1:"+k2.get());
            System.out.println("thread1:"+k3.get());
            System.out.println();
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread2:"+k1.get());
            System.out.println("thread2:"+k2.get());
            System.out.println("thread2:"+k3.get());

            k1.set("abc");
            k2.set(2);
            k3.set(k3);

            System.out.println("thread2:"+k1.get());
            System.out.println("thread2:"+k2.get());
            System.out.println("thread2:"+k3.get());
            System.out.println();
        });

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread3:"+k1.get());
            System.out.println("thread3:"+k2.get());
            System.out.println("thread3:"+k3.get());

            k1.set("haha");
            k2.set(10);
            k3.set(new Object());

            System.out.println("thread3:"+k1.get());
            System.out.println("thread3:"+k2.get());
            System.out.println("thread3:"+k3.get());
            System.out.println();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main:"+k1.get());
        System.out.println("main:"+k2.get());
        System.out.println("main:"+k3.get());
    }
}
