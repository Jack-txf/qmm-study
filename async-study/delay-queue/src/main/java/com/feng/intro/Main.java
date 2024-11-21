package com.feng.intro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Task> task = createTask();
        for (Task task1 : task) {
            TaskDelayQueue.addTask(task1);
        } // 添加了任务
        System.out.println("============================消费者开始消费消息");
        // 消费者消费消息
        while ( !TaskDelayQueue.isEmpty() ) {
            Task t = TaskDelayQueue.getTop();
            System.out.println("【消费者消费消息】" + t);
        }

    }

    private static List<Task> createTask() {
        Calendar calendar = Calendar.getInstance();
        List<Task> tasks = new ArrayList<>();
        for ( int i = 1; i <= 10; ++i ) {
            Task task = new Task();
            task.setName("任务" + i);
            task.setType(i);
            if ( (i & 1) == 1 ) calendar.set(Calendar.SECOND, 20 - i);
            else calendar.set(Calendar.SECOND, 50 + i);

            Date time = calendar.getTime();
            task.setLastTimes( time.getTime() - System.currentTimeMillis());
            task.setEndTime(calendar.getTime());

            tasks.add(task);
        }
        return tasks;
    }
}
