package com.feng.multilogs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/3/22
 */
public class MultiLogsApp {
    static String path = "D:\\报表\\接口访问日志";
    static List<String> tips = Arrays.asList("module: 商品搜索管理", "module: 天天抽奖", "module: 秒杀活动", "module: 订单", "module: 会员", "module: 支付模块");
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try(
                Stream<Path> stream = Files.walk(Paths.get(path))
        ) {
            List<String> filePaths = stream
                    .filter(Files::isRegularFile)
                    .filter(path1 -> !path1.getFileName().toString().contains(".gz"))
                    .map(Path::toString)
                    .collect(Collectors.toList());
            System.out.println("=========日志文件数量： " + filePaths.size());

            List<Future<Set<String>>> tasks = filePaths.stream()
                    .map(filePath -> pool.submit(
                            () -> processFile(filePath)
                    ))
                    .collect(Collectors.toList());
            // 获取结果
            Set<String> res = new HashSet<>();
            for (Future<Set<String>> task : tasks) {
                res.addAll(task.get());
            }
            System.out.println("=========访问用户数量： " + res.size());

            // 总访问次数
            // long ans = 0;
            // for (Future<Long> task : tasks) {
            //     try {
            //         ans += task.get();
            //     } catch (Exception e) {
            //         e.printStackTrace();
            //     }
            // }
            // System.out.println("=========总访问次数： " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
            System.out.println("=========程序结束");
        }
    }


    /*
    module: 商品搜索管理
    module: 天天抽奖
    module: 秒杀活动
    module: 订单
    module: 会员
    module: 支付模块

    userId: xxxx
     */
    public static Set<String> processFile(String filePath){
        long sum = 0;
        // 访问用户
        Set<String> userIds = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 访问次数
                // for (String tip : tips) {
                //     if (line.contains(tip)) {
                //         ++sum;
                //     }
                // }
                // 访问用户
                if (line.startsWith("userId: ")) {
                    userIds.add(line.substring(8));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userIds;
    }
}
