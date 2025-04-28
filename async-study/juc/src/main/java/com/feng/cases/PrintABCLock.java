package com.feng.cases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 15:18
 * @注释
 */
public class PrintABCLock {
    private Lock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();
    private int flag = 1;
    public static void main(String[] args) {
        PrintABCLock obj = new PrintABCLock();
        new Thread(obj::printA).start();
        new Thread(obj::printB).start();
        new Thread(obj::printC).start();
    }
    public void printA()  {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                while ( flag != 1 ) a.await();
                System.out.println('A'); flag = 2;
                b.signal();
            }
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
    public void printB() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                while ( flag != 2 ) b.await();
                System.out.println('B'); flag = 3;
                c.signal();
            }
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                while ( flag != 3 ) c.await();
                System.out.println('C'); flag = 1;
                a.signal();
            }
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

}
