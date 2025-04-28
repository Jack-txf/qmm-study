package com.feng.base;

import java.util.concurrent.locks.StampedLock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 10:29
 * @注释
 */
public class StampedLockTest {
    private static final StampedLock stampedLock = new StampedLock();
    private static double x = 1.0;
    private static double y = 1.0;
    public static void main(String[] args) {
        // 1. 一个线程写
        new Thread(() -> addXY(1, 1)).start();
        // 2. 10个线程读
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(getSArea())).start();
        }
    }
    private static double getSArea() {
        // 乐观读
        long stamp = stampedLock.tryOptimisticRead();
        double s1 = x * y;
        // 验证一下
        if (!stampedLock.validate(stamp)) { // 验证失败
            stamp = stampedLock.readLock();
            try {
                s1 = x * y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return s1;
    }
    private static void addXY(double a, double b) {
        long stamp = stampedLock.writeLock();
        try {
            //long lock = stampedLock.writeLock(); 不可重入
            System.out.println("写进行~~");
            x += a;
            y += b;
            Thread.sleep(1000);
            System.out.println("写ok~~");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
}
