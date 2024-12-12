package com.feng.lqb10;

import java.util.Scanner;

public class LQ2097 {
    public static int[] st = null;
    public static int[] pre = null;
    public static int n, x;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); x = scan.nextInt();
        st = new int[n+1]; pre = new int[n+1];
        for (int i = 1; i <= n-1; i++) {
            st[i] = scan.nextInt();
            pre[i] += pre[i-1] + st[i]; // 前缀和数组
        }
        pre[n] = pre[n-1];

        int l = 1, r = n, ans = 0;
        while (l <= r) {
            int mid = (l+r) >> 1;
            if (check(mid)) { // 当前步长可以跳完
                ans = mid;
                r = mid -1 ;
            }
            else l = mid + 1;
        }
        System.out.println(ans);
        scan.close();
    }

    public static boolean check( int mid ) {
        for (int i = 1; i < n - mid + 1; i++) {
            if (pre[i + mid - 1] - pre[i - 1] < 2 * x)return false;
        }
        return true;
    }
}
