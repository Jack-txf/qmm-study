package com.feng.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
/*
案例：
    5个启动线程【模拟启动工作】
    2个工作线程【模拟工作--要在启动工作完成之后才能执行】
    3个收尾工作线程【模拟资源回收等操作--要在工作线程做完之后才能执行】
 */
public class CountDownLatchDemo {
    private static final CountDownLatch start = new CountDownLatch(5);
    private static final CountDownLatch work = new CountDownLatch(2);
    private static final CountDownLatch end = new CountDownLatch(3);
    private static List<Thread> createThread( int count, int type ) {
        String name;
        if ( type == 1 ) name = "启动线程";
        else if ( type == 2 ) name = "工作线程";
        else name = "收尾线程";
        List<Thread> group = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            group.add(new Thread(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "完成!");
                    if ( type == 1 ) start.countDown();
                    else if ( type == 2 ) work.countDown();
                    else end.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, name + i));
        }
        return group;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> startGroup = createThread(5, 1);
        List<Thread> workGroup = createThread(2, 2);
        List<Thread> endGroup = createThread(3, 3);

        startGroup.forEach(Thread::start);
        start.await();
        System.out.println("===========================");

        workGroup.forEach(Thread::start);
        work.await();
        System.out.println("===========================");

        endGroup.forEach(Thread::start);
        end.await();
        System.out.println("===========================");
    }
}
