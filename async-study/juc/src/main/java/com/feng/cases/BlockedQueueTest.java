package com.feng.cases;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 16:12
 * @注释
 */
public class BlockedQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    }
}

class TBlockedQueue<T> {
    public int capacity;
    public int size;

    public TBlockedQueue(int capacity) {
        this.capacity = capacity;
    }

    // 1. 入队 -- 当队列已满时，向队列中添加元素的操作会被阻塞，直到队列有空间可用。
    //public void put();
    // 2. 出队 -- 当队列为空时，从队列中获取元素的操作会被阻塞，直到队列中有新元素加入
}
