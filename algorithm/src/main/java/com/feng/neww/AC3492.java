package com.feng.neww;

import java.io.*;
import java.util.*;

/*
 * java写算法！！！ 新东西---输入输出的别样写法！！！！！！！！！！！！！！！！！！！
 */
public class AC3492 {
    static final BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static int n, m;
    static int[] aval;
    static PriorityQueue<int[]>[] tasks = null;
    //比较器：根据时间终止
    static Comparator<int[]> comps = Comparator.comparingInt(o -> o[0]);
    public static void main(String[] args) throws IOException {
        String[] ss = cin.readLine().split(" ");
        n = Integer.parseInt(ss[0]);
        m = Integer.parseInt(ss[1]);

        aval = new int[n+1];
        tasks = new PriorityQueue[n+1];

        ss = cin.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            aval[i] = Integer.parseInt(ss[i - 1]);
            // 初始化每个计算机的任务队列
            tasks[i] = new PriorityQueue<>(comps);
        }
        int t, tar, nt, ca; // 何时分配任务  指定哪个计算机  耗时  耗费的算力
        for (int i = 1; i <= m; i++) {
            ss = cin.readLine().split(" ");
            // 输入上面四个变量
            t = Integer.parseInt(ss[0]); tar = Integer.parseInt(ss[1]); nt = Integer.parseInt(ss[2]); ca = Integer.parseInt(ss[3]);
            // 1.先恢复算力
            PriorityQueue<int[]> tmpTask = tasks[tar];
            while ( !tmpTask.isEmpty() && tmpTask.peek()[0] <= t ) {
                aval[tar] += tmpTask.poll()[1];
            }
            // 2.再看得不得行
            if ( aval[tar] < ca ) {
                out.println("-1");
            } else {
                //任务终止时间、消耗的算力
                int[] works = {t+nt, ca};
                tmpTask.offer(works);
                aval[tar] -= ca;
                out.println(aval[tar]);
            }
        }
        out.flush();
    }

}
