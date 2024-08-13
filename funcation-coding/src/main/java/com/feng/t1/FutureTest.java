package com.feng.t1;

import java.util.concurrent.*;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/9
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Student student = new Student();

        long start = System.currentTimeMillis();
        // 现在有四个任务
        // student里面的方法都是slee
        //
        //
        //
        //
        // p一秒
        CompletableFuture<String> singFuture = CompletableFuture.supplyAsync(student::sing, pool);
        CompletableFuture<Integer> computeFuture = CompletableFuture.supplyAsync(student::compute, pool);
        CompletableFuture<Boolean> resFuture = CompletableFuture.supplyAsync(student::res, pool);
        CompletableFuture<Object> objectFuture = CompletableFuture.supplyAsync(student::haha, pool);

        System.out.println("主线程忙别的去了：!!!!!!!!!");
        TimeUnit.SECONDS.sleep(1);

        String sing = singFuture.get();
        Integer integer = computeFuture.get();
        Boolean res = resFuture.get();
        Object stu = objectFuture.get();

        long end = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + " 获得结果：耗时 (" + (end - start) + ")");
        System.out.println(sing);
        System.out.println(integer);
        System.out.println(res);
        System.out.println(stu);

        pool.shutdown();
    }



}
