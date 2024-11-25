package com.feng.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/*
创建了树 TreeNode 之后，如果我们想要并行地对树中的所有值求和，我们需要实现一个 RecursiveTask<Integer> 接口。每个任务都接收自己的节点，并将其值添加到其子节点的值之和上。

要计算子节点值的总和，任务实现执行以下操作

1、 将子节点集合转换为流(stream)；
2、 映射前面操作中创建的流，为每个元素创建一个新的CountingTask；
3、 通过fork执行每个子任务；
4、 通过在每个fork任务上调用join()方法来收集结果；
5、 使用·Collectors.summingInt\收集器对结果求和；
 */
public class CountingTask extends RecursiveTask<Integer> {

    private final ForkMain.TreeNode node;

    public CountingTask(ForkMain.TreeNode node) {
        this.node = node;
    }

    @Override
    protected Integer compute() {
        return node.value + node.children.stream()
                .map(childNode -> new CountingTask(childNode).fork())
                .mapToInt(ForkJoinTask::join).sum();
    }
}
