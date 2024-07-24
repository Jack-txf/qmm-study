package com.feng.async.sp.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;

/*
 修改默认的线程池！！！！
 */
//@Configuration
//@EnableAsync
//@Slf4j
//@EnableScheduling
//public class ChangeDefaultThreadConfig implements SchedulingConfigurer {
//
//    private static final ThreadPoolTaskExecutor EXECUTOR = new VisibleThreadPoolTaskExecutor();
//
//    /**
//     * 功能描述:  线程池
//     */
//    @Bean
//    @Primary
//    public ThreadPoolTaskExecutor asyncServiceExecutor() {
//        log.info("start asyncService Executor");
//        //使用visibleThreadPoolTaskExecutor
//
//        // 通过Runtime方法来获取当前服务器cpu内核，根据cpu内核来创建核心线程数和最大线程数
//        int availableProcessors = Runtime.getRuntime().availableProcessors();
//        /*
//         * 配置线程个数
//         如果是CPU密集型任务，那么线程池的线程个数应该尽量少一些，一般为CPU的个数+1条线程(大量计算)
//         如果是IO密集型任务，那么线程池的线程可以放的很大，如2*CPU的个数(IO操作)
//         */
//        EXECUTOR.setCorePoolSize(availableProcessors + 1);
//        // 允许线程池超时
//        EXECUTOR.setAllowCoreThreadTimeOut(true);
//        //配置最大线程数
//        EXECUTOR.setMaxPoolSize(availableProcessors * 4);
//        // 空闲存活时间
//        EXECUTOR.setKeepAliveSeconds(60);
//        // 设置 等待终止秒数
//        EXECUTOR.setAwaitTerminationSeconds(60);
//        //配置队列大小
//        EXECUTOR.setQueueCapacity(availableProcessors * 100);
//        //配置线程池中的线程的名称前缀
//        EXECUTOR.setThreadNamePrefix("async-thread-pool-");
//        // 等待所有任务结束再关闭线程池
//        EXECUTOR.setWaitForTasksToCompleteOnShutdown(true);
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        EXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //执行初始化
//        EXECUTOR.initialize();
//        return EXECUTOR;
//    }
//
//    /*
//     * 创建一个定长线程池，支持定时及周期性任务执行
//     */
//    @Bean
//    public ThreadPoolTaskScheduler scheduledThreadPoolExecutor() {
//        log.info("start Scheduled Executor");
//        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
//        // 大于等于 任务数量
//        executor.setPoolSize(10);
//        executor.setThreadNamePrefix("scheduled-thread");
//        // 等待时长
//        executor.setAwaitTerminationSeconds(60);
//        // 调度器shutdown被调用时等待当前被调度的任务完成
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        //设置饱和策略
//        //CallerRunsPolicy：线程池的饱和策略之一，当线程池使用饱和后，直接使用调用者所在的线程来执行任务；如果执行程序已关闭，则会丢弃该任务
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        return executor;
//    }
//
//    /*
//     * 功能描述:  配置 @Scheduled 定时器所使用的线程池
//     * 配置任务注册器：ScheduledTaskRegistrar 的任务调度器
//     * "@Scheduled" 默认是单线程执行的，所以在需要的时候，我们可以设置一个线程池去执行定时任务。
//     */
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        //可配置两种类型：TaskScheduler、ScheduledExecutorService
//        // scheduledTaskRegistrar.setScheduler(scheduledThreadPoolExecutor());
//        //只可配置一种类型：taskScheduler
//        scheduledTaskRegistrar.setTaskScheduler(scheduledThreadPoolExecutor());
//    }
//
//    /**
//     * 优雅关闭线程池
//     */
//    @PreDestroy
//    private void shutdownGracefully() {
//        log.error("ThreadPool close");
//    }
//
//}
//
