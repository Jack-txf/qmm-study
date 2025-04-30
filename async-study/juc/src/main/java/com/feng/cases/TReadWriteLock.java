package com.feng.cases;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/30 9:31
 * @注释 自定义读写锁
 */
public class TReadWriteLock {
    private final Sync sync;
    private final ReadLock readLock;
    private final WriteLock writeLock;
    public TReadWriteLock() {
        sync = new Sync();
        readLock = new ReadLock(sync);
        writeLock = new WriteLock(sync);
    }
    // 对外暴露读写锁
    public Lock readLock() {return readLock;}
    public Lock writeLock() {return writeLock;}
    // 内置同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        static final int SHARED_SHIFT = 16;
        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        // 写锁方法（tryAcquire/tryRelease）
        protected boolean tryAcquire(int acquires) {
            Thread current = Thread.currentThread();
            int state = getState();
            int writeCount = getWriteCount(state);
            // 如果存在读锁或写锁（且持有者不是当前线程），获取失败
            if (state != 0) {
                if (writeCount == 0 || current != getExclusiveOwnerThread())
                    return false;
            }
            // 检查是否超过最大重入次数（低16位是否溢出）
            if (writeCount + acquires > EXCLUSIVE_MASK)
                throw new Error("超出最大重入次数");
            // CAS更新写锁状态
            if (compareAndSetState(state, state + acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
            return false;
        }
        protected boolean tryRelease(int releases) {
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            int newState = getState() - releases;
            boolean free = (getWriteCount(newState) == 0);
            if (free)
                setExclusiveOwnerThread(null);
            setState(newState);
            return free;
        }

        // 读锁方法（tryAcquireShared/tryReleaseShared）
        protected int tryAcquireShared(int acquires) {
            Thread current = Thread.currentThread();
            int state = getState();

            // 如果有其他线程持有写锁，且不是当前线程（允许锁降级），则获取失败
            if (getWriteCount(state) != 0 && getExclusiveOwnerThread() != current)
                return -1;

            // 计算读锁数量
            int readCount = getReadCount(state);
            if (readCount == (1 << SHARED_SHIFT) - 1)
                throw new Error("超出最大读锁数量");

            // CAS增加读锁计数（高16位）
            if (compareAndSetState(state, state + (1 << SHARED_SHIFT))) {
                return 1; // 成功获取读锁
            }
            return -1; // 需要进入队列等待
        }
        protected boolean tryReleaseShared(int releases) {
            for (;;) {
                int state = getState();
                int readCount = getReadCount(state);
                if (readCount == 0)
                    throw new IllegalMonitorStateException();

                // CAS减少读锁计数
                int newState = state - (1 << SHARED_SHIFT);
                if (compareAndSetState(state, newState)) {
                    return readCount == 1; // 最后一个读锁释放时可能触发唤醒
                }
            }
        }
        // 其他辅助方法
        int getReadCount(int state) { return state >>> SHARED_SHIFT; }
        int getWriteCount(int state) { return state & EXCLUSIVE_MASK; }
    }

    // 读锁（共享）
    public static class ReadLock implements Lock {
        private final Sync sync;
        public ReadLock(Sync sync) { this.sync = sync; }
        public void lock() { sync.acquireShared(1); }
        public void unlock() { sync.releaseShared(1); }
        // 其他方法（略）
        @Override
        public Condition newCondition() {
            return null;
        }
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

    }

    // 写锁（独占）
    public static class WriteLock implements Lock {
        private final Sync sync;
        public WriteLock(Sync sync) { this.sync = sync; }
        public void lock() { sync.acquire(1); }
        public void unlock() { sync.release(1); }
        // 其他方法（略）
        @Override
        public Condition newCondition() {
            return null;
        }
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
    }

}
