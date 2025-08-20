package com.feng.bst.real;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/8/20
 */
public class BinarySearchTree {
    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
    }
    public BinarySearchTree(Node t) {
        this.root = t;
    }

    // 删除--最新版
    public void removeByValue( int val ) {
        Node cur = root, curFa = null;
        // 1.先找到val所在结点，及其父亲结点
        while ( cur != null ) {
            if ( cur.getVal() == val ) { break;}
            else if ( cur.getVal() > val ) {
                curFa = cur;
                cur = cur.getLeft();
            } else {
                curFa = cur;
                cur = cur.getRight();
            }
        }
        // 2.1 说明找不到val的结点
        if ( cur == null ) { return; }
        // 2.2 如果cur只有一个子节点或者无子节点
        if ( cur.getLeft() == null || cur.getRight() == null ) {
            if ( cur.getLeft() == null ) {
                if ( curFa == null ) // 说明删除的是根节点
                    root = cur.getRight();
                else { // 不是删除根节点
                    if ( curFa.getLeft() == cur ) curFa.setLeft(cur.getRight());
                    else curFa.setRight(cur.getRight());
                }
            } else {
                if ( curFa == null ) // 说明删除的是根节点
                    root = cur.getLeft();
                else { // 不是删除根节点
                    if ( curFa.getLeft() == cur ) curFa.setLeft(cur.getLeft());
                    else curFa.setRight(cur.getLeft());
                }
            }
        } else {
            // 2.3 如果cur有两个子节点
            // 找到右子树的最小结点 --- 然后直接改指针就行了~~~~~~~ TODO
            Node rightMin = cur.getRight(), minFa = cur;
            while ( rightMin.getLeft() != null ) {
                minFa = rightMin;
                rightMin = rightMin.getLeft();
            }
            // 把cur和rightMin的val交换
            exchange(cur,  rightMin);
            // 然后删除cur右子树的最左边结点
            removeMostLeft(rightMin, minFa);
        }
    }
    private void exchange(Node cur, Node rmin ) {
        int val = cur.getVal();
        cur.setVal(rmin.getVal());
        rmin.setVal(val);
    }
    private void removeMostLeft( Node cur, Node curFa ) {
        while ( cur.getLeft() != null ) {
            curFa = cur;
            cur = cur.getLeft();
        }
        if ( curFa.getLeft() == cur ) curFa.setLeft(cur.getRight());
        else curFa.setRight(cur.getRight());
    }

    // 添加结点
    public boolean add( int val ) {
        if ( isEmpty() ) {
            root = new Node(val);
            size++;
            return true;
        } else {
            boolean flag = addNode0(val);
            if (flag) { size++; }
            return flag;
        }
    }

    private boolean addNode0(int val) {
        Node cur = root;
        while ( cur != null ) {
            if ( cur.getVal() > val ) {
                if ( cur.getLeft() == null ) {
                    cur.setLeft(new Node(val));
                    return true;
                } else {
                    cur = cur.getLeft();
                }
            } else if ( cur.getVal() < val ) {
                if ( cur.getRight() == null ) {
                    cur.setRight(new Node(val));
                    return true;
                } else {
                    cur = cur.getRight();
                }
            }
            else break;
        }
        return false;
    }

    // 查找结点
    public Node find( int val ) {
        Node[] nodeAndFather = findNodeAndFather(val);
        if ( nodeAndFather == null ) { // 说明找不到
            return null;
        } else { // 找到了
            return nodeAndFather[0];
        }
    }
    // 找到指定节点及其父结点 node[0]：指定结点，node[1]：父亲结点
    private Node[] findNodeAndFather( int val ) {
        Node cur = root, father = null;
        while ( cur != null ) {
            if ( cur.getVal() == val ) {
                return new Node[] { cur, father };
            } else if ( cur.getVal() > val ) {
                father = cur;
                cur = cur.getLeft();
            } else {
                father = cur;
                cur = cur.getRight();
            }
        }
        return null;
    }

    // 删除结点
    /**
     * 删除非根结点的情况：
     * 1. 删除的结点既没有左儿子也没有右儿子,就可直接删除了【删除叶子结点】
     * 2. 被删除的结点只有一个孩子！【让被删除结点的父结点指针 指向 被删除结点的孩子就行了】【1,2可以合并为一个】
     * 3. 被删除的结点有俩孩子，找出被删除结点右边最小的结点，与被删除结点交换，然后再删除右边的最小结点
     * @param val
     * @return 删除的结点
     */

    // public void remove( int val ) {
    //     if ( root == null ) { return; }
    //     if ( val == root.getVal() ) {
    //         removeRoot(); // 删除根节点
    //     } else remove0(root, val);
    // }

    private void removeRoot() {
        size--;
        if ( root.getLeft() != null && root.getRight() != null ) {
            remove0(root, root.getVal());
        } else if ( root.getLeft() == null && root.getRight() != null ) {
            root = root.getRight();
        } else if ( root.getLeft() != null ) {
            root = root.getLeft();
        } else {
            root = null;
        }
    }

    private void remove0( Node node, int val ) {
        if ( node == null ) { return ; }
        // 1.找到需要被删除的结点, 及其父结点
        Node[] nodeAndFather = findNodeAndFather(val);
        if ( nodeAndFather == null ) {  // 说明找不到要删除的
            return ;
        }
        Node removeNode = nodeAndFather[0];
        Node father = nodeAndFather[1];
        Node[] sons = judgeRemove(removeNode); // 被删除结点有几个孩子
        if ( sons.length <= 1 ) { // 【情况一：removeNode是叶子结点，或者只有一个孩子节点】
            // removeNode.setLeft(null); removeNode.setRight(null);
            // 看一下
            if ( father.getLeft() == removeNode ) {
                if ( sons.length == 0 ) father.setLeft(null);
                else father.setLeft(sons[0]);
            } else if ( father.getRight() == removeNode ) {
                if ( sons.length == 0 ) father.setRight(null);
                else father.setRight(sons[0]);
            }
        } else { // 【情况二：removeNode有两个孩子节点】
            // 找到被删除结点右边最小的结点
            Node rightMin = findRightMin(removeNode.getRight());
            // rightMin与removeNode的值交换一下 TODO 交换指针
            // 这里仅仅是交换值
            int tmp = removeNode.getVal();
            removeNode.setVal(rightMin.getVal());
            rightMin.setVal(tmp);
            // 删除右树最小的结点
            remove0(removeNode.getRight(), val);
        }
    }

    private Node findRightMin( Node node ) {
        Node cur = node;
        while ( cur != null ) {
            if ( cur.getLeft() == null ) {
                return cur;
            }
            cur = cur.getLeft();
        }
        return null;
    }
    private Node[] judgeRemove( Node cur ) {
        List<Node> l = new ArrayList<>();
        if ( cur.getLeft() != null ) l.add(cur.getLeft());
        if ( cur.getRight() != null ) l.add(cur.getRight());
        return l.toArray(new Node[0]);
    }

    // 树空
    public boolean isEmpty() {
        return root == null || size == 0;
    }

    // 层序遍历
    public List<List<Integer>> ceng() {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = q.pollFirst();
                if ( cur.getLeft() != null ) q.add(cur.getLeft());
                if ( cur.getRight() != null ) q.add(cur.getRight());
                list.add(cur.getVal());
            }
            res.add(list);
        }
        return res;
    }

}
