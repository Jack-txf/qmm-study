package com.feng.tools;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/5/2
 */
public class FutureJdk8Tools {
    public static void main(String[] args) throws Exception {
        // queryTotal();
        // queryHuoBi();
        // queryWebsite();
        queryTotal2();
    }
    //====================== 查询多个库汇总结果
    public static void queryTotal2() throws Exception {
        // 1.线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(5, 10, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        List<String> tasks = Arrays.asList("A库:1000", "B库:2000", "C库:3000", "D库:1500");
        // 构建任务列表
        List<CompletableFuture<Integer>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(() -> queryDataBase(task), pool))
                .collect(Collectors.toList());
        CompletableFuture<List<Integer>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> futures.stream()
                    .mapToInt(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .boxed()
                    .collect(Collectors.toList())
                , pool2);
        List<Integer> integers = result.get();
        System.out.println(integers);
        pool.shutdown();
        pool2.shutdown();
    }
    public static Integer queryDataBase( String db ) {
        String[] s1 = db.split(":");
        String dbName = s1[0];
        int time = Integer.parseInt(s1[1]);
        try {
            System.out.println(Thread.currentThread().getName() + "正在查询" + dbName);
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + "查询" + dbName + " ok");
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        return time;
    }
    //====================== 搜索结果
    public static void queryWebsite() throws Exception {
        List<String> tasks = Arrays.asList("百度:1200", "CSDN:2000", "博客园:1500", "谷歌:3000");
        // 构建任务列表
        List<CompletableFuture<String>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(() -> searchRes(task)))
                .collect(Collectors.toList());
        // 等待所有任务完成
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futures.toArray(new CompletableFuture[0]));
        Object result = anyOf.get();
        System.out.println(result + "最先返回了结果");
    }
    public static String searchRes( String webSite ) {
        String[] s1 = webSite.split(":");
        String webSiteName = s1[0];
        int time = Integer.parseInt(s1[1]);
        System.out.println(Thread.currentThread().getName() + "正在查询" + webSite);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        System.out.println(Thread.currentThread().getName() + "查询" + webSite + " ok");
        return webSiteName;
    }
    //====================== 货比多家
    public static void queryHuoBi() throws Exception {
        List<String> tasks = Arrays.asList("A店:100", "B店:200", "C店:300", "D店:50"); // 每个店的价格信息
        // 构建任务列表
        List<CompletableFuture<Integer>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(() -> taskComputePrice(task)))
                .collect(Collectors.toList());
        // 等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.join();
        // 遍历得到最小值
        int minPrice = futures.stream()
                .mapToInt(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .min()
                .orElseThrow(() -> new IllegalStateException("No price found"));
        System.out.println("最小价格：" + minPrice);
    }
    public static int taskComputePrice(String msg ) {
        String[] split = msg.split(":");
        String store = split[0];
        int price = Integer.parseInt(split[1]);
        System.out.println(Thread.currentThread().getName() + "正在查询" + store + "价格");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        System.out.println(Thread.currentThread().getName() + "查询" + store + "价格 ok");
        return price;
    }
    //====================== 查询汇总
    public static void queryTotal() throws ExecutionException, InterruptedException {
        // 1.线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(FutureJdk8Tools::queryA, pool);
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(FutureJdk8Tools::queryB, pool);
        // 2.合并两个future
        CompletableFuture<String> res = futureB
                .thenCombine(futureA, (b, a) -> "A库" + a + "--B库" + b) // 合并两个future的结果
                .exceptionally(e -> "出现异常了"); // 处理异常
        String resStr = res.get();
        System.out.println("【汇总结果】" + resStr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");
        pool.shutdown();
    }
    public static String queryA() {
        System.out.println(Thread.currentThread().getName() + "正在查询A库");
        try {
            Thread.sleep(1500);
            // int a = 1 / 0; // 模拟一个异常
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        System.out.println(Thread.currentThread().getName() + "查询A库 ok");
        return "A库信息";
    }
    public static String queryB() {
        System.out.println(Thread.currentThread().getName() + "正在查询B库");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        System.out.println(Thread.currentThread().getName() + "查询B库 ok");
        return "B库信息";
    }
    //======================
}
