package com.feng.t1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/9
 */
public class App {
    public Map<String, String> test() throws InterruptedException, ExecutionException {
        // 不存在并发插入情况，不需要使用ConcurrentHashMap
//		Map<String, String> data = new ConcurrentHashMap<>(3);
        Map<String, String> data = new HashMap<>(3);
        //第一个任务。
        CompletableFuture<String> task01 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task01";
        });
        //第二个任务。
        CompletableFuture<String> task02 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task02";
        });
        // 第三个任务
        CompletableFuture<String> task03 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task03";
        });
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

        System.out.println("now: " + formatter.format(LocalDateTime.now()));
        // get()方法会阻塞
        data.put("task01",task01.get());



        System.out.printf("task01执行完毕;当前时间:%s\n",formatter.format(LocalDateTime.now()));
        data.put("task02",task02.get());
        System.out.printf("task02执行完毕;当前时间:%s\n",formatter.format(LocalDateTime.now()));
        data.put("task03",task03.get());
        System.out.printf("task03执行完毕;当前时间:%s\n",formatter.format(LocalDateTime.now()));
        return data;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new App().test();
    }
}
