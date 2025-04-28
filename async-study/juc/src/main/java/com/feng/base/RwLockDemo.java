package com.feng.base;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
使用synchronized 块和使用 Lock API 之间几乎没有区别：

同步块完全包含在方法中 : 在独立的方法中，我们可以使用 Lock 提供的 lock() 和 unlock() 实现锁和解锁操作。

同步块不支持公平竞争，任何线程都可以获取释放的锁定，且不能指定优先级。但锁 ( Lock ) 就不一样了，可以通过指定公平属性来实现 Lock 中的公平性。这可以确保最长的等待线程被授予锁定权限。

如果线程无法访问同步块，则会阻塞该线程。Lock 则提供了 tryLock() 方法。线程只有在可用且不被任何其他线程保持时才获取锁定。这减少了线程等待锁定的阻塞时间。
处于 「 等待 」 状态以获取对同步块的访问的线程不能被中断。Lock 提供了一个 lockInterruptibly() 方法，可用于在等待锁定时中断线程。
 */
public class RwLockDemo {

    /*
    ReadWriteLock
    读锁 : 如果没有线程获得写锁定或请求它，则多个线程可以获取读锁定。
    写锁 : 如果没有线程正在读或写，则只有一个线程可以获取写锁。
     */
    //当一个变量被 volatile 修饰时，任何线程对它的写操作都会立即刷新到主内存中，并且会强制让缓存了该变量的线程的数据清空，
    //必须从主内存重新读取最新数据。
    private static final Map<String,Object> data = new HashMap<>();

    // 创建一个读写锁的对象
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 写操作
    public static void write(String key, Object value) {
        readWriteLock.writeLock().lock(); // 获得写锁
        try {
            System.out.println(Thread.currentThread().getName() + "：正在执行写操作" + key);
            TimeUnit.MILLISECONDS.sleep(500);
            data.put(key, value);
            System.out.println(Thread.currentThread().getName() + "：写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();//释放锁
        }
    }
    //取数据
    public static Object get(String key) {
        readWriteLock.readLock().lock();
        Object result = null;
        //暂停一会
        try {
            System.out.println(Thread.currentThread().getName() + "：正在执行读操作" + key);
            TimeUnit.MILLISECONDS.sleep(500);
            result = data.get(key);
            System.out.println(Thread.currentThread().getName() + "已经取完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }
        return result;
    }

    public static void main(String[] args) {
        //创建线程往里面放数据
        for (int i = 0; i < 5; i++) {
            int num = 1;
            new Thread(()->{
                write(""+num, num);
            }, String.valueOf(i)).start();
        }
        //创建线程取数据
        for(int i = 0; i < 5; i++) {
            int num = i;
            new Thread(()->{
                get(""+num);
            }, String.valueOf(i)).start();
        }
    }

    private static void time() {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.HOUR_OF_DAY, -16);
        System.out.println(instance.getTime());
    }


}
