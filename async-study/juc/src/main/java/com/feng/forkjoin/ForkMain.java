package com.feng.forkjoin;


import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/*
ForkJoinPool 继承了 AbstractExecutorService抽象类

ForkJoinPool 线程池并不会为每个子任务创建一个单独的线程，相反，池中的每个线程都有自己的双端队列用于存储任务
这种架构使用了一种名为工作窃取（ work-stealing ）算法来平衡线程的工作负载。
工作窃取（ work-stealing ）算法
简单来说，就是 空闲的线程试图从繁忙线程的 deques 中 窃取 工作。
默认情况下，每个工作线程从其自己的双端队列中获取任务。但如果自己的双端队列中的任务已经执行完毕，双端队列为空时，工作线程就会从另一个繁忙线程的双端队列尾部或全局入口队列中获取任务，因为这是最大概率可能找到工作的地方。
这种方法最大限度地减少了线程竞争任务的可能性。它还减少了工作线程寻找任务的次数，因为它首先在最大可用的工作块上工作。
 */
public class ForkMain {
    public static void main(String[] args) {
        demo();
    }

    public static void test1() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        //线程池将使用 10 个处理器核心
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(10);
    }

    /*
    ForkJoinTask 是 ForkJoinPool 线程之中执行的任务的基本类型。我们日常使用时，一般不直接使用 ForkJoinTask ，而是扩展它的两个子类中的任意一个
        1、 任务不返回结果(返回void）的RecursiveAction；
        2、 返回值的任务的RecursiveTask<V>；
        这两个类都有一个抽象方法 compute() ，用于定义任务的逻辑。
        要做的，就是继承任意一个类，然后实现 compute() 方法。
     */
    public static void test2() {
        ForkJoinTask.invokeAll();
    }

    //使用 ForkJoinPool 遍历节点树并计算所有叶值之和的简单示例
    public static void demo() {// 【最重要的是递归拆分任务】=-------------------------------------
        TreeNode tree = new TreeNode(5,
                new TreeNode(3), new TreeNode(2,
                    new TreeNode(2), new TreeNode(8)
                ));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int sum = forkJoinPool.invoke(new CountingTask(tree));
        System.out.println(sum);
    }
    static class TreeNode {
        int value;
        Set<TreeNode> children;
        TreeNode(int value, TreeNode... children) {
            this.value = value;
            this.children = Sets.newHashSet(children);
        }
    }

}
