package com.feng.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaitDemo {
    private static final int r = 100;
    private int signal = 100;
    private final List<Integer> goods = new ArrayList<>();

    public synchronized void addGood() throws InterruptedException {
        Random random = new Random(100);
        while ( true ) {
            if ( goods.size() >= 100 ) { // 满了
                this.wait(); // wait notify notifyAll 必须结合 synchronized 使用
            }
            goods.add(random.nextInt());
        }
    }
}
