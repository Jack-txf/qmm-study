package com.feng.chat.job.normal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Williams_Tian
 * @CreateDate 2024/9/5
 */

@Slf4j
@Component
public class TestJob {

    // 这两个定时任务 --- 说明这只有一个线程在抢着运行
    // @Scheduled(cron = "1-59 * * * * ?")
    // public void hello() {
    //     try {
    //         TimeUnit.SECONDS.sleep(5);
    //     } catch (InterruptedException e) {
    //         throw new RuntimeException(e);
    //     }
    //     log.info("hello");
    // }
    //
    // @Scheduled(cron = "1-59 * * * * ?")
    // public void world() {
    //     log.info("world");
    // }


    // 下面使用了线程池！！，就不用争抢了
    // @Async(value = "customThreadExecutor")
    // @Scheduled(cron = "1-59 * * * * ?")
    // public void hello() {
    //     try {
    //         TimeUnit.SECONDS.sleep(5);
    //     } catch (InterruptedException e) {
    //         throw new RuntimeException(e);
    //     }
    //     log.info("hello");
    // }
    //
    // @Async(value = "customThreadExecutor")
    // @Scheduled(cron = "1-59 * * * * ?")
    // public void world() {
    //     log.info("world");
    // }
}
