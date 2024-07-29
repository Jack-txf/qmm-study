package com.feng.custom02;

import lombok.Data;

import java.util.HashMap;

@Data
public class Node {
    private boolean isEnd;
    private HashMap<Character, Node> next;

    public Node() {
        this.isEnd = false;
        this.next = new HashMap<>();
    }
}