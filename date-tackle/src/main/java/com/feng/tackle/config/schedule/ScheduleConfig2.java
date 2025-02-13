//package com.feng.tackle.config.schedule;
//
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
///**
// * @version 1.0
// * @Author txf
// * @Date 2025/2/13 11:03
// * @注释 第二种方法 ---- 实际上是一毛一样的。。和第一个方法
// */
//public class ScheduleConfig2 implements SchedulingConfigurer {
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(5); // 设置线程池大小
//        scheduler.setThreadNamePrefix("my-scheduler-");
//        scheduler.initialize();
//        taskRegistrar.setTaskScheduler(scheduler);
//    }
//}
