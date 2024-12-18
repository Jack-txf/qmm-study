package com.feng.lqb10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
病毒溯源
 */
public class AC3468 {
    public static int n;
    public static int[] fa; // 父节点
    public static List<List<Integer>> g = null;
    public static List<Integer> ansXl = new ArrayList<>(); // 答案序列
    public static void dfs(int u, List<Integer> xl) {
        if ( g.get(u).size() == 0 ) { // 到了叶子节点了
            //for (Integer integer : xl) {
            //    System.out.print(integer + " ");
            //}
            //System.out.println();

            if ( xl.size() > ansXl.size() ) {
                ansXl = new ArrayList<>(xl);
            } else if ( xl.size() == ansXl.size() ){
                for (int i = 0; i < xl.size(); i++) {
                    if ( xl.get(i) > ansXl.get(i)) break;
                    if ( xl.get(i) < ansXl.get(i) ) {
                        ansXl = new ArrayList<>(xl);
                        break;
                    }
                }
            }
            return ;
        }
        for ( int i = 0; i < g.get(u).size(); i++) {
            Integer tar = g.get(u).get(i);
            xl.add(tar);
            dfs(tar, xl);
            xl.remove(xl.size()-1);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        fa = new int[n+1];
        g = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());

        Arrays.fill(fa, -1); // 默认父节点为-1
        int nums, p;
        for (int i = 0; i < n; i++) {
            nums = in.nextInt(); // 输入变异数量
            while ( nums-- > 0 ) {
                p = in.nextInt(); // 变异成哪个了
                fa[p] = i;
                g.get(i).add(p);
            }
        }

        // 找到根节点
        int root = 0;
        for (int i = 0; i < n; i++) {
            if ( fa[i] == -1 ) {
                root = i;
                break;
            }
        }

        //
        List<Integer> xl = new ArrayList<>();
        xl.add(root);
        dfs(root, xl);

        //
        System.out.println(ansXl.size());
        for (Integer integer : ansXl) {
            System.out.print(integer + " ");
        }
        System.out.println();

        in.close();;
    }
}
