package com.feng.lqb10;

import java.util.Arrays;
import java.util.Scanner;

public class AC1209 {
    public static int n = 0, res = 0;
    public static boolean[] book = new boolean[10];
    public static int[] nums = new int[10];
    public static int cal( int l, int r ) {
        if ( l == -1 ) l = 1;
        if ( r == -1 ) r = 9;
        int t = 0;
        for ( int i = l; i <= r; i++) {
            t = t * 10 + nums[i];
        }
        return t;
    }
    //  1 2 3 4 5 6 7 8
    // x x x x x x x x x
    public static void dfs( int now ) {
        if ( now == 10 ) { // 如果全排列完了
            for ( int l = 1; l < 8; l++) {
                for ( int r = l + 1; r <= 8; r++) {
                    int a = cal( 1, l);
                    int b = cal( l+1, r);
                    int c = cal( r+1, 9);
                    if ( b %c == 0 && a + b / c == n ) {
                        res++;
                    }
                }
            }
            return;
        }
        for ( int i = 1; i <= 9; i++) {
            if ( !book[i] ) {
                nums[now] = i;
                book[i] = true;
                dfs( now + 1);
                book[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        dfs(1);
        System.out.println(res);
        scan.close();
    }
}
