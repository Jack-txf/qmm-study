package com.feng.lqb10;

import java.util.*;

public class AC3491 {
    public static long n;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextLong();
        long ans = 1;
        for ( long i = 2; i <= n && n >= 1; i++) {
            long count = 0;
            while (n % i == 0) {
                ++count;
                n /= i;
            }
            if ( (count & 1) == 1 ) {
                ans *= i;
            }

            if ( isPrime(n) ) { // 如果n是质数, 提前结束循环，节省时间
                ans *= n;
                break;
            }
        }
        System.out.println(ans);
        in.close();
    }

    private static boolean isPrime(long n) {
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if ( n % i == 0 ) return false;
        }
        return true;
    }
}
