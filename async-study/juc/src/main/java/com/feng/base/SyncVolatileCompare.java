package com.feng.base;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/5/2
 */
public class SyncVolatileCompare {
    private boolean flag = false; // 非volatile变量
    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }
    public synchronized boolean getFlag() {
        return flag;
    }
    public static void main(String[] args) throws InterruptedException {
        SyncVolatileCompare syncVolatileCompare = new SyncVolatileCompare();
        new Thread(() -> {
            System.out.println("【线程1】开始等待 flag 变为 true...");
            while (!syncVolatileCompare.getFlag()) {
                // ...
            }
            System.out.println("【线程1】检测到 flag 已变为 true，退出循环");
        }).start();

        Thread.sleep(1000);
        Thread thread = new Thread(() -> {
            System.out.println("【线程2】开始修改 flag...");
            try {
                Thread.sleep(500); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            syncVolatileCompare.setFlag(true);
        });
        thread.start();
    }

}
