package com.feng.base;



public class VolatileTest {
    private boolean flag = false;
    private int counter = 0; // 新增共享变量

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        Thread thread = new Thread(() -> {
            while (!test.isFlag()) {
                test.counter++; // 增加读写操作
                try {
                    Thread.sleep(10); // 缩短休眠时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Counter: " + test.counter);
        });
        thread.start();
        Thread.sleep(1000);
        test.setFlag(true);
        thread.join();
    }
}