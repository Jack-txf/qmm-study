package com.feng.lqb10;

import java.util.Arrays;
import java.util.Scanner;

public class Acwing125 {
    public static int Cow[][] = null;
    public static int n;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        Cow = new int[n+1][2];
        for (int i = 1; i <= n ; i++) {
            Cow[i][0] = in.nextInt(); Cow[i][1] = in.nextInt();
        }
        Arrays.sort(Cow, 1, n+1, (o1, o2) -> o2[0] - o1[0] ); // 排序下标范围： [formIndex, toIndex)

        int ans = -0x3f3f3f3f, total = 0, nowDanger;
        for (int i = n-1; i >= 1; i--) {
            total += Cow[i+1][0];

            nowDanger = total - Cow[i][1];
            ans = Math.max(ans, nowDanger);
        }

        System.out.println(ans);
        in.close();
    }
}
