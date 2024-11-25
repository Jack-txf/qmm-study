package com.feng.pool1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PoolMain1 {
    public static void main(String[] args) {
        test2();
    }
    /*
    ExecutorService 中的 submit() 方法被重载为支持 Runnable 或 Callable ，它们都是功能接口，可以接收一个 lambda 作为参数（ 从 Java 8 开始 ）：
        使用 Runnable 作为参数的方法不会抛出异常也不会返回任何值 ( 返回 void )
        使用 Callable 作为参数的方法则可以抛出异常也可以返回值。
        如果想让编译器将参数推断为 Callable 类型，只需要 lambda 返回一个值即可。
     */
    public static void test1() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    /*
    ThreadPoolExecutor
    ThreadPoolExecutor 创建的线程池由固定数量的核心线程组成，这些线程在 ThreadPoolExecutor 生命周期内始终存在，除此之外还有一些额外的线程可能会被创建，并会在不需要时主动销毁。
    corePoolSize 参数用于指定在线程池中实例化并保留的核心线程数。如果所有核心线程都忙，并且提交了更多任务，则允许线程池增长到 maximumPoolSize

    keepAliveTime 参数是额外的线程（ 即，实例化超过 corePoolSize 的线程 ）在空闲状态下的存活时间。
     */

    public static void test2() {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2); // 可以强转
        executor.submit(() -> {
            Thread.sleep(1000);
            System.out.println(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            System.out.println(3000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            System.out.println(2000);
            return null;
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 不能强转
    }

    /*
    Executors.newCachedThreadPool()
Executors 还提供了 Executors.newCachedThreadPool() 静态方法创建另一个预配置的 ThreadPoolExecutor。该方法创建的线程池没有任何核心线程，因为它将 corePoolSize 属性设置为 0，但同时有可以创建最大数量的额外线程，
因为它将 maximumPoolSize 设置为 Integer.MAX_VALUE ，且将 keepAliveTime 的值设置为 60 秒。这些参数值意味着缓存的线程池可以无限制地增长以容纳任何数量的已提交任务。
但是，当不再需要线程时，它们将在 60秒不活动后被销毁。这种线程池的使用场景一般是你的应用程序中有很多短期任务。



     */
}
