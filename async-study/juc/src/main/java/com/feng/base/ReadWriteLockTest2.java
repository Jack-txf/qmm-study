package com.feng.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 9:49
 * @注释
 */
public class ReadWriteLockTest2 {
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();
    private static int[] a = new int[10];
    public static void main(String[] args) throws InterruptedException {
        // 1个线程写
        new Thread(ReadWriteLockTest2::write).start();
        for (int i = 0; i < 9; i++)  // 10个线程读
            new Thread(()-> System.out.println(get())).start();
        Thread.sleep(2000);
    }
    public static Object get() {
        readLock.lock();
        try {
            Thread.sleep(100);
            return a[1];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }
    public static void write() {
        writeLock.lock();
        try {
            //writeLock.lock();
            a[1]++;
            System.out.println("写进行~~~");
            Thread.sleep(1000);
            System.out.println("写ok~~~");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //writeLock.unlock(); 可重入
            writeLock.unlock();
        }
    }
}
