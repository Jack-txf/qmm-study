package com.feng.cases;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/28 14:42
 * @注释
 */
public class PrintABCSynchronized {
    private int now = 1;
    public static void main(String[] args) {
        PrintABCSynchronized obj = new PrintABCSynchronized();
        new Thread(obj::printA).start();
        new Thread(obj::printB).start();
        new Thread(obj::printC).start();
    }
    public void printA() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                while ( now != 1 ) {
                    try {this.wait();}
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A"); now = 2;
                this.notifyAll();
            }
        }
    }
    public void printB() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                while ( now != 2 ) {
                    try {this.wait();}
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B"); now = 3;
                this.notifyAll();
            }
        }
    }
    public void printC() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                while ( now != 3 ) {
                    try {this.wait();}
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C"); now = 1;
                this.notifyAll();
            }
        }
    }

}
