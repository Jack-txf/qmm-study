package com.feng.lqb10;

public class LQ1462 {
    public static final int END = (1 << 21) - 1;
    /*
     f[i][j] 表示最后走i点时，状态为j的方案数
     */
    public static long[][] f = new long[22][END+5];
    public static  int[][] g = new int[22][22];
    public static void main(String[] args) {
        // 构建图
        for (int i = 1; i <= 21; i++) {
            for ( int j = i+1; j <= 21; ++j ) {
                if ( gcd(i, j) == 1 ) { // 如果是互质的
                    g[i][j] = g[j][i] = 1;
                } else g[i][j] = g[j][i] = 0;
            }
        }

        // 状压dp初始化
        f[1][1] = 1;
        // 枚举状态
        for (int j = 1; j <= END; j++) { // 枚举状态
            for (int i = 1; i <= 21; i++) { // 枚举点
                // 如果i在这个状态里面，不在的话不符合f数组的定义
                if ( (j & (1 << (i-1))) != 0 ) {
                    // 枚举下一个点
                    for (int k = 1; k <= 21; k++) {
                        if ( g[i][k] == 1 && (j & (1 << (k-1))) == 0) { // 从i可以到k
                            f[k][j | 1<<(k-1)] += f[i][j];
                        }
                    }
                }
            }
        }

        long ans = 0;
        for ( int i = 1; i <= 21; ++i ) {
            if ( g[i][1] == 1 ) {
               ans += f[i][END];
            }
        }

        System.out.println(ans);
    }

    // 判断两个数是否互质
    public static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
