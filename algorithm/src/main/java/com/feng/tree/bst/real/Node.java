package com.feng.tree.bst.real;


import lombok.Data;
import java.util.Objects;

/**
 * @Description: 二叉搜索树的结点
 * @Author: txf
 * @Date: 2025/8/20
 */
@Data
public class Node {
    private int val;
    private Node left;
    private Node right;
    public Node() {
        left = right = null;
    }
    public Node(int val) {
        this.val = val;
        left = right = null;
    }
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return val == node.val && Objects.equals(left, node.left)
                && Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + left.getVal() +
                ", right=" + right.getVal() +
                '}';
    }
}
