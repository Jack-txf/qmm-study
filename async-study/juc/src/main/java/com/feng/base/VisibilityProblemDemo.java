package com.feng.base;

public class VisibilityProblemDemo {
    private static volatile boolean flag = false; // 非volatile 变量
    public static void main(String[] args) {
        // 线程1：持续检查 flag 是否变为 true
        new Thread(() -> {
            System.out.println("【线程1】开始等待 flag 变为 true...");
            while (!flag) {
                // 空循环，无其他操作
            }
            System.out.println("【线程1】检测到 flag 已变为 true，退出循环");
        }).start();

        // 主线程休眠 1 秒，确保线程1已启动
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程2：修改 flag 为 true
        new Thread(() -> {
            try {
                Thread.sleep(500); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true; // 修改共享变量
            System.out.println("【线程2】已将 flag 设为 true");
        }).start();
    }
}