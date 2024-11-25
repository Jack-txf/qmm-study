package com.feng.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Executor 顶级接口
  ExecutorService 继承上面的接口
    AbstractExecutorService 实现上面的ExecutorService接口
      ThreadPoolExecutor 继承抽象类AbstractExecutorService
Executors 一个创建ExecutorService的工具类
    Executors。FinalizableDelegatedExecutorService内部类 继承 DelegatedExecutorService
 */
public class TestClass {
    public static void main(String[] args) {

    }

    public static void test1() {
        ExecutorService service = Executors.newFixedThreadPool(10);
    }

    public static void test2() {
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 可以用lambda表达式简写
        //Callable<String> callableTask1 = new Callable<String>() {
        //    @Override
        //    public String call() throws Exception {
        //        TimeUnit.MILLISECONDS.sleep(300);
        //        return "Task's execution";
        //    }
        //};
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
    }

}
