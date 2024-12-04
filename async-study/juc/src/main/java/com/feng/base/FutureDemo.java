package com.feng.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureDemo {
    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<>(new CallTask());
        new Thread(task, "sss").start();

        try {
            String s = task.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class CallTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return "10";
    }
}
