package com.feng.lqb10;

import java.util.Scanner;

public class LQ19716 {
    public static int n, m;
    public static int[][] range = null;
    public static int[] c = null; // 差分数组
    public static int[] pre = null; // 原数组，c数组的前缀和数组
    public static int[] r0 = null; // pre数组[1, i]区间中1的个数
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); m = scan.nextInt(); // 输入n, m
        // 数组new出来
        range = new int[m+1][2];
        c = new int[n+3];  pre = new int[n+3]; r0 = new int[n+3];

        for (int i = 1; i <= m; i++) {
            range[i][0] = scan.nextInt();
            range[i][1] = scan.nextInt(); // 输入区间
            c[range[i][0]] += 1;
            c[range[i][1]+1] -= 1;
        }
        // 求出原数组pre
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i-1] + c[i];
            if ( pre[i] == 0 ) cnt++;
        }
        // 求出pre数组[1,i]区间中1的个数
        for (int i = 1; i <= n; i++) {
            if ( pre[i] == 1 ) r0[i] = r0[i-1] + 1;
            else r0[i] = r0[i-1];
        }
        int l, r;
        for (int i = 1; i <= m; i++) {
            l = range[i][0]; r = range[i][1]; // 左右区间
            // 求出r0数组中[l, r]区间，1的个数
            int num0 = r0[r] - r0[l - 1];
            System.out.println(cnt + num0);
        }
        scan.close();
    }
}
