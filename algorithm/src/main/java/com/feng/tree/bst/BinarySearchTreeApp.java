package com.feng.tree.bst;

import com.feng.tree.bst.real.BinarySearchTree;
import com.feng.tree.bst.real.Node;

/**
 * @Description: 二叉搜索树
 * @Author: txf
 * @Date: 2025/8/20
 */
public class BinarySearchTreeApp {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(5); bst.add(4); bst.add(3); bst.add(2);
        bst.add(7); bst.add(6); bst.add(8);

        Node node = bst.find(5);
        System.out.println(node);

        bst.removeByValue(5);

        bst.ceng().forEach(System.out::println);

    }
}
