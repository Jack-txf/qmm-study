package com.feng.lqb10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LQ19722 {
    public static int[][] dp = null;
    public static boolean[] isPrime = null;
    public static  int n;
    public static int[] nums = null;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); // 输入n
        // 数组初始化
        nums = new int[n+3];
        // 输入n个数
        int maxn = 0;
        for (int i = 1; i <= n; i++) {
            nums[i] = scan.nextInt();
            maxn = Math.max(maxn, nums[i]);
        }
        // dp数组初始化
        dp = new int[maxn+3][2];  isPrime = new boolean[maxn+3];
        // n以内的所有质数，从小到大排列的
        List<Integer> prime = getPrime(n);
        dp[1][1] = 0; dp[1][0] = 1;

        for (int i = 1; i <= maxn; i++) {
            if ( isPrime[i] ) { // 如果是质数，谁先手谁赢
                dp[i][0] = 0; dp[i][1] = 1;
            } else {
                int posi = getLastPrime(i, prime), k;
                for ( k = posi; k >= 0; --k ) {
                    if ( dp[i - prime.get(k)][0] == 1 ) {
                        dp[i][1] = 1;  dp[i][0] = 0;
                        break;
                    }
                }
                if ( k == -1 ) {
                    dp[i][1] = 0;
                    dp[i][0] = 1;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(dp[nums[i]][1]);
        }
        scan.close();
    }

    // 在prime质数数组中找到最后一个小于target的质数的位置
    public static int getLastPrime(int target, List<Integer> prime) {
        int l = 0, r = prime.size()-1, position = -1;
        while (l <= r) {
            int mid = l + r >> 1;
            if ( prime.get(mid) < target) {
                position = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return position;
    }

    // 筛选出1-n的所有质数
    public static List<Integer> getPrime(int n) {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 2; i*j <= n; j++) {
                isPrime[i*j] = false;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }
}
