package com.feng.lqb10;

import java.util.HashSet;
import java.util.Scanner;

public class LQ587 {
    public static int n =  1200000;
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        int end =  n / 2;
        for (int i = 1; i <= end; i++) {
            int tmp = n / i;
            if ( n % i == 0 ) set.add(i);
            if ( tmp * i == n ) set.add(tmp);
        }
        System.out.println(set.size());
    }
}
