package com.feng.custom02;

import lombok.Data;

import java.util.HashMap;

@Data
class Node {
    private boolean isEnd;
    private HashMap<Character, Node> next;

    public Node() {
        this.isEnd = false;
        this.next = new HashMap<>();
    }
}