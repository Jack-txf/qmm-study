package com.feng.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 抽象队列同步器（AQS）使用：
 *      实现一个同一时刻至多只支持两个线程同时执行的同步器
 */

// 让当前类继承Lock接口
public class TwinLock implements Lock {

    // 定义锁允许的最大线程数
    private final static int DEFAULT_SYNC_COUNT = 2;
    // 创建一个锁对象，用以进行线程同步，Sync继承自AQS
    private final Sync sync = new Sync(DEFAULT_SYNC_COUNT);

    // 以内部类的形式实现一个同步器类，也就是锁，这个锁继承自AQS
    private static final class Sync extends AbstractQueuedSynchronizer {

        // 构造方法中指定锁支持的线程数量
        Sync(int count) {
            // 若count小于0，则默认为2
            if (count <= 0) {
                count = DEFAULT_SYNC_COUNT;
            }
            // 设置初始同步状态
            setState(count);
        }
        
        /**
         * 重写tryAcquireShared方法，这个方法用来修改同步状态state，也就是获取锁
         */
        @Override
        protected int tryAcquireShared(int arg) {
            // 循环尝试
            for (; ; ) {
                // 获取当前的同步状态
                int nowState = getState();
                // 计算当前线程获取锁后，新的同步状态
                // 注意这里使用了减法，因为此时的state表示的是还能支持多少个线程
                // 而当前线程如果获得了锁，则state就要减小
                int newState = nowState - arg;
                
                // 如果newState小于0，表示当前已经没有剩余的资源了
                // 则当前线程不能获取锁，此时将直接返回小于0的newState；
                // 或者newState>0，就会执行compareAndSetState方法修改state的值，
                // 若修改成功将，将返回大于0的newState；
                // 若修改失败，则表示有其他线程也在尝试修改state，此时循环一次后，再次尝试
                if (newState < 0 || compareAndSetState(nowState, newState)) {
                    return newState;
                }
            }
        }

        /**
         * 尝试释放同步状态
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                // 获取当前同步状态
                int nowState = getState();
                // 计算释放后的新同步状态，这里使用加法，
                // 表示有线程释放锁后，当前锁可以支持的线程数量增加了
                int newState = nowState + arg;
                // 使用CAS修改同步状态，若成功则返回true，否则自旋
                if (compareAndSetState(nowState, newState)) {
                    return true;
                }
            }
        }
        
    }


    /**
     * 获取锁的方法
     */
    @Override
    public void lock() {
        // 这里调用的是AQS的模板方法acquireShared，
        // 在acquireShared中将调用我们重写的tryAcquireShared方法
        // 传入参数为1表示当前线程，当前线程获取锁后，state将-1
        sync.acquireShared(1);
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        // 这里调用的是AQS的模板方法releaseShared，
        // 在acquireShared中将调用我们重写的tryReleaseShared方法
        // 传入参数为1表示当前线程，当前线程释放锁后，state将+1
        sync.releaseShared(1);
    }

    /*******************其他需要实现的方法省略***************************/

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
