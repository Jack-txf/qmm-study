package com.feng.tools;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT, () -> System.out.println("所有子任务处理完成，准备进入下一步！"));

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }

    static class Worker implements Runnable {
        private final int threadNumber;
        private final CyclicBarrier barrier;

        Worker(int threadNumber, CyclicBarrier barrier) {
            this.threadNumber = threadNumber;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程 " + threadNumber + " 正在处理任务");
                Thread.sleep(2000); // 模拟任务处理时间
                System.out.println("线程 " + threadNumber + " 完成任务，等待其他线程");
                barrier.await(); // 等待其他线程完成
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
