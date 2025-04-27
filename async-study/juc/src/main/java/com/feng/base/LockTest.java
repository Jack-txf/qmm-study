package com.feng.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/27 14:42
 * @注释
 */
public class LockTest {
    ReentrantLock lock = new ReentrantLock(true);
    int count = 0;

    public static void main(String[] args) throws InterruptedException {
        LockTest test = new LockTest();
        for (int i = 1; i <= 2; i++) {
            new Thread(test::add).start();
        }
        Thread.sleep(2000);
        System.out.println(test.count);
    }

    public void add() {
        try {
            lock.lock();
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
        }

    }
}
