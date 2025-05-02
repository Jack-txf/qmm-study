package com.feng.tools;

import java.util.concurrent.Semaphore;

/**
 * @Description: 停车场管理
 * @Author: txf
 * @Date: 2025/5/2
 */
// 停车场管理，模拟一个停车场，最多允许5辆车同时停放。车辆（线程）需获取停车位（许可证）才能进入，离开时释放许可证。
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        // 创建几辆车
        new Thread(new Car("迈巴赫1", semaphore)).start();
        new Thread(new Car("保时捷2", semaphore)).start();
        new Thread(new Car("奔驰3", semaphore)).start();
        new Thread(new Car("宝马4", semaphore)).start();
        new Thread(new Car("法拉利5", semaphore)).start();
        new Thread(new Car("五菱6", semaphore)).start();
        new Thread(new Car("本田7", semaphore)).start();

    }

    static class Car implements Runnable {
        private final String name;
        private final Semaphore semaphore;
        public Car(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                System.out.println("car " + Thread.currentThread().getId() + ":" + name + " 获取停车位中~~~");
                semaphore.acquire();
                System.out.println("car " + Thread.currentThread().getId() + ":" +  name + " 停车了");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("car " + Thread.currentThread().getId() + ":" +  name + " 离开车位");
                semaphore.release();
            }
        }
    }
}
