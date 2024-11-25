package com.feng.pool1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SchedulePool {
    public static void main(String[] args) {
        test2();
    }
    /*
    schedule() 方法允许在指定的延迟后执行一次任务

    scheduleAtFixedRate() 方法允许在指定的初始延迟后执行任务，然后以一定的周期重复执行，其中 period 参数用于指定两个任务的开始时间之间的间隔时间，因此任务执行的频率是固定的。

    scheduleWithFixedDelay() 方法类似于 scheduleAtFixedRate() ，它也重复执行给定的任务，但period 参数用于指定前一个任务的结束和下一个任务的开始之间的间隔时间。
也就是指定下一个任务延时多久后才执行。执行频率可能会有所不同，具体取决于执行任何给定任务所需的时间。
     */
    public static void test1() {
        //ScheduledThreadPoolExecutor extends 【ThreadPoolExecutor】 implements 【ScheduledExecutorService】 相当于ThreadPoolExecutor的扩展

        //静态方法 Executors.newScheduledThreadPool() 方法用于创建包含了指定 corePoolSize，无上限 maximumPoolSize 和 0 存活时间 keepAliveTime 的 ScheduledThreadPoolExecutor 实例。
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    }

    public static void test2() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        // 延迟500毫秒执行
        //executor.schedule(() -> {
        //    System.out.println("Hello World");
        //}, 500, TimeUnit.MILLISECONDS);

        // 延迟500毫秒执行, 每1000毫秒执行一次
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }
}
