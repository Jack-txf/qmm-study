package com.feng.exp;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/5/2
 */
public class TestAppException {
    public static void main(String[] args) {
        // System.out.println(test());
        // System.out.println(test1());
        System.out.println(test2());
    }
    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            System.out.print("3");
        }
    }

    public static int test1() {
        try {
            return 2;
        } finally {
            return 3;
        }
    }

    public static int test2() {
        int i = 0;
        try {
            i = 2;
            return i;
        } finally {
            i = 3;
            System.out.println(i);
        }
    }
}
