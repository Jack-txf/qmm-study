package com.feng.neww;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AC4645 {
    static int n, m, x;
    static int[] dp = null;
    static BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static Map<Long, Integer> map = new HashMap<>(); // a[i] ^ x 最近出现的下标
    public static void main(String[] args) throws IOException {
        String[] lines = cin.readLine().split(" ");
        n = Integer.parseInt(lines[0]);
        m = Integer.parseInt(lines[1]);
        x = Integer.parseInt(lines[2]); // 输入n,m,x
        dp = new int[n+3];

        lines = cin.readLine().split(" ");
        long a, b;
        for (int i = 1; i <= n; i++) {
            b = Long.parseLong(lines[i-1]);
            a = b ^ x;
            if ( map.containsKey(a) ) {
                dp[i] = Math.max(dp[i-1], map.get(a));
            } else {
                dp[i] = dp[i-1];
            }
            map.put(b, i);
        }

        // m次查询
        while ( m-- > 0 ) {
            lines = cin.readLine().split(" ");
            int l = Integer.parseInt(lines[0]);
            int r = Integer.parseInt(lines[1]);
            if ( l != r && dp[r] >= l ) out.println("yes");
            else out.println("no");
        }
        out.flush();
    }
}
