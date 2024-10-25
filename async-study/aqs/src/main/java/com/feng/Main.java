package com.feng;

import com.feng.sync.TwinLock;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个我们自定义的锁对象
        Lock lock = new TwinLock();

        // 启动10个线程去尝试获取锁
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()->{
                // 循环执行
                while (true) {
                    // 获取锁
                    lock.lock();
                    try {
                        // 休眠1秒
                        Thread.sleep(1000);
                        // 输出线程名称
                        System.out.println(Thread.currentThread().getName());
                        // 再次休眠一秒
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 释放锁
                        lock.unlock();
                    }
                }
            });
            // 将线程设置为守护线程，主线程结束后，收获线程自动结束
            t.setDaemon(true);
            t.start();
        }

        // 主线程每隔1秒输出一个分割行
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("********************************");
        }
    }

}
