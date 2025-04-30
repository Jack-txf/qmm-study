package com.feng.cases;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/30 10:55
 * @注释
 */
public class CustomLockTest {
    private TReadWriteLock readWriteLock;
    private Lock readLock;
    private Lock writeLock;
    private Map<String, String> data;

    public CustomLockTest() {
        readWriteLock = new TReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
        data = new HashMap<>();
    }

    public static void main(String[] args) {
        CustomLockTest obj = new CustomLockTest();
        // 两个线程写
        new Thread(() -> obj.write("key", "value"), "写Thread-1").start();
        new Thread(() -> obj.write("key", "value5"), "写Thread-2").start();
        // 4个线程读
        for (int i = 0; i < 4; i++) {
            new Thread(() -> System.out.println(obj.read("key")), "读" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("main线程结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void write(String key, String value) {
        writeLock.lock();
        try {
            System.out.println( Thread.currentThread().getName() + "写入中~~~");
            TimeUnit.SECONDS.sleep(1);
            data.put(key, value);
            System.out.println( Thread.currentThread().getName() + "写入ok~~~");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
    public String read(String key) {
        readLock.lock();
        try {
            System.out.println( Thread.currentThread().getName() + "读取中~~~");
            TimeUnit.SECONDS.sleep(2);
            System.out.println( Thread.currentThread().getName() + "读取ok~~~" + data.get(key));
            return data.get(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }
}
