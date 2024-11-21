package com.feng.intro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private static List<Task> createTask() {
        List<Task> tasks = new ArrayList<>();
        for ( int i = 1; i <= 10; ++i ) {
            Task task = new Task();
            task.setName("任务" + i);
            task.setType(i);
            task.setEndTime(new Date());
        }
        return tasks;
    }
}
