package com.feng.lqb10;

public class LeetcodeDp {
    public int climbStairs(int n) {
        if ( n == 1 || n == 2 ) return n;
        int f1 = 1, f2 = 2, f3 = 0;
        for ( int i = 3; i <= n; ++i ) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] f = new int[cost.length+1];
        f[0] = cost[0];
        f[1] = cost[1];

        for ( int i = 2; i < cost.length; ++i ) {
            f[i] = cost[i] + Math.min(f[i-1], f[i-2]);
        }
        return Math.min(f[cost.length], f[cost.length-1]);
    }

    // 打家劫舍 - 1
    public int rob(int[] nums) {
        int n = nums.length;
        if ( n == 1 ) return nums[0];

        int[] f = new int[n+1];
        f[0] = nums[0];
        f[1] = Math.max(f[0], nums[1]);
        for ( int i = 2; i < n; ++i ) {
            f[i] = Math.max(f[i-1], f[i-2] + nums[i]);
        }
        return f[n-1];
    }

    // // 删除并获得点数
    // public int deleteAndEarn(int[] nums) {
    //
    // }
}
