package com.feng.intro;

import java.util.concurrent.DelayQueue;

public class TaskDelayQueue {
    private static final DelayQueue<Task> delayQueue = new DelayQueue<>(); // 创建一个延时队列

    public static void addTask( Task task ) {
        boolean offer = delayQueue.offer(task);
        if ( offer ) System.out.println("【添加任务成功】 " + task);
        else System.out.println("【添加任务 失败!】 " + task);
    }

    public static boolean isEmpty() {
        return delayQueue.isEmpty();
    }

    public static Task getTop() throws InterruptedException {
        return delayQueue.take(); // 要用take哦！！！！！！！！！！！
    }
}
