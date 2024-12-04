package com.feng.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
阻塞队列：
Queue(interface) --- BlockingQue(interface) === ArrayBlockingQueue(class)
                                            === LinkedBlockingQueue(class)

阻塞队列案例：
    2个消费者线程--从阻塞队列（队头）里面获取元素
    1个生产者线程--向阻塞队列里面添加元素
 */
public class BlockingQue {
    private static final BlockingQueue<Integer> bq = new LinkedBlockingQueue<>(10);
    public static void main(String[] args) {
        // new Thread(() -> {
        //     while ( true ) {
        //         try {
        //             bq.put(1); // 放入1
        //             System.out.println(Thread.currentThread().getName() + " 【放入】 1, 队列容量: " + bq.size());
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }, "生产者线程").start();

        new Thread(() -> {
            while ( true ) {
                try {
                    Integer take = bq.take();// 取出
                    System.out.println(Thread.currentThread().getName() + " 取出 " + take);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者1").start();
        //
        // new Thread(() -> {
        //     while ( true ) {
        //         try {
        //             Integer take = bq.take();// 取出
        //             System.out.println(Thread.currentThread().getName() + " 取出 " + take);
        //             Thread.sleep(600);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }, "消费者2").start();

    }
}
