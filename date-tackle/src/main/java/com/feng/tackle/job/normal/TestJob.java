package com.feng.tackle.job.normal;

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

    // ==================================================================================================================== 案例1 ----start
    // 这两个定时任务 --- 说明这只有一个线程在抢着运行
    /*
    hello()按道理来说应该是5秒打印一次，world()应该是2秒打印一次。但是只有一个线程，故都会间隔7秒钟。
    Spring默认的定时任务调度是基于 单线程 的 TaskScheduler 实现的。这意味着所有的 @Scheduled 任务默认会共享同一个线程，如果某个任务执行时间过长或阻塞，可能会影响其他任务的调度和执行。
    这是一个线程任务队列
    =========================================
    word(), hello(), word(), hello() .................
    =========================================
     */
     //@Scheduled(cron = "1-59 * * * * ?")
     //public void hello() {
     //    try {
     //        log.info("hello");
     //        TimeUnit.SECONDS.sleep(5);
     //    } catch (InterruptedException e) {
     //        throw new RuntimeException(e);
     //    }
     //
     //}
     //@Scheduled(cron = "1-59 * * * * ?")
     //public void world() {
     //    try {
     //        log.info("world");
     //        TimeUnit.SECONDS.sleep(2);
     //    } catch (InterruptedException e) {
     //        throw new RuntimeException(e);
     //    }
     //}
     // ==================================================================================================================== 案例1 ---- end

     // ==================================================================================================================== 案例2 ---- start
     // 下面使用了线程池！！，就不用争抢了
     //@Async(value = "customThreadExecutor")
     //@Scheduled(cron = "1-59 * * * * ?")
     //public void hello() {
     //    try {
     //        log.info("hello---任务开始");
     //        TimeUnit.SECONDS.sleep(5);
     //        log.info("hello---任务结束");
     //    } catch (InterruptedException e) {
     //        throw new RuntimeException(e);
     //    }
     //}
     //@Async(value = "customThreadExecutor")
     //@Scheduled(cron = "1-59 * * * * ?")
     //public void world() {
     //    try {
     //        log.info("world---任务开始");
     //        TimeUnit.SECONDS.sleep(2);
     //        log.info("world---任务结束");
     //    } catch (InterruptedException e) {
     //        throw new RuntimeException(e);
     //    }
     //}
     // ==================================================================================================================== 案例2 ---- end

    // ==================================================================================================================== 案例3 ---- start
    // 没有指定线程池 ---- 每次都创建一个新的线程
    //@Async
    //@Scheduled(cron = "0/20 * * * * ? ")
    //public void hello() {
    //    try {
    //        log.info("hello---任务开始");
    //        TimeUnit.SECONDS.sleep(10);
    //        log.info("hello---任务结束");
    //    } catch (InterruptedException e) {
    //        throw new RuntimeException(e);
    //    }
    //}
    //@Async
    //@Scheduled(cron = "0/20 * * * * ? ")
    //public void world() {
    //    try {
    //        log.info("world---任务开始");
    //        TimeUnit.SECONDS.sleep(10);
    //        log.info("world---任务结束");
    //    } catch (InterruptedException e) {
    //        throw new RuntimeException(e);
    //    }
    //}
    // ==================================================================================================================== 案例3 ---- end

    // ==================================================================================================================== 案例4 ---- start
    // 第一个单线程，第二个线程池
    @Scheduled(cron = "20/40 * * * * ? ") // 从每分钟的20秒开始，每40秒执行一次，----  xx:xx:20  xx:xx+1:20...
    public void hello() {
        try {
            log.info("hello---任务开始");
            TimeUnit.SECONDS.sleep(20);
            log.info("hello---任务结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Async(value = "customThreadExecutor")
    @Scheduled(cron = "30/20 * * * * ? ") // 从每分钟的30开始，每20秒执行一次，----  xx:xx:30  xx:xx:50 xx:xx+1:30  xx:xx+1:50...
    public void world() {
        try {
            log.info("world---任务开始");
            TimeUnit.SECONDS.sleep(10);
            log.info("world---任务结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // ==================================================================================================================== 案例4 ---- end
}
