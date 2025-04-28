package com.feng.cases;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 16:12
 * @注释
 */
public class BlockedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        TBlockedQueue<Integer> queue = new TBlockedQueue<>(5);
        queue.add(8);queue.add(10);
        // 生产者消费者模型
        BlockedQueueTest obj = new BlockedQueueTest();
        // 1个线程往里面put
        Thread producer = new Thread(() -> obj.producer(queue), "生产者 one");
        // 2个线程从队列里面拿
        Thread consumer = new Thread(() -> obj.consumer(queue), "消费者 one");
        Thread consumer2 = new Thread(() -> obj.consumer(queue), "消费者 two");

        producer.start();
        consumer.start();
        consumer2.start();

        producer.join();
        consumer.join();
        consumer2.join();
    }

    public void producer( TBlockedQueue<Integer> q) {
        int i = 1;
        while ( i < 10 ) {
            q.put(i);
            try {
                Thread.sleep(500);
                System.out.println("【" + Thread.currentThread().getName()+ "放入】: " + i + " 耗时500毫秒");
                ++i;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void consumer(TBlockedQueue<Integer> q) {
        int i = 1;
        while ( i++ < 10 ) {
            Integer take = q.take();
            try {
                Thread.sleep(500);
                System.out.println("【"+Thread.currentThread().getName()+"取出】: " + take + " 耗时500毫秒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class TBlockedQueue<T> {
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private final int capacity;
    private final LinkedList<T> list;

    public TBlockedQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity 不能小于1");
        this.capacity = capacity;
        list = new LinkedList<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }
    public void add( T t ) {
        list.addLast(t);
    }

    // 1. 入队 -- 当队列已满时，向队列中添加元素的操作会被阻塞，直到队列有空间可用。
    public void put( T t ) {
        if (t == null) throw new NullPointerException();
        lock.lock();
        try {
            while (list.size() == capacity) notFull.await();
            list.addLast(t);
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    // 2. 出队 -- 当队列为空时，从队列中获取元素的操作会被阻塞，直到队列中有新元素加入
    public T take() {
        lock.lock();
        try {
            while (list.isEmpty()) notEmpty.await();
            T t = list.removeFirst();
            notFull.signal();
            return t;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
