package com.feng.custom02;

import java.util.Set;

public class SensitiveWordDFA {

    // 构建 DFA 树
    public static Node buildDFATree(Set<String> sensitiveWords) {
        Node root = new Node();
        for (String word : sensitiveWords) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.getNext().containsKey(c)) {
                    curr.getNext().put(c, new Node());
                }
                curr = curr.getNext().get(c);
            }
            curr.setEnd(true);
        }
        return root;
    }

    // 敏感词过滤
    public static String filterSensitiveWords(String text, Node root) {
        StringBuilder result = new StringBuilder();
        Node curr = root;
        int start = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (curr.getNext().containsKey(c)) {
                curr = curr.getNext().get(c);
                if (curr.isEnd()) {
                    String sensitiveWord = text.substring(start, i + 1);
                    for (int j = 0; j < sensitiveWord.length(); j++) {
                        result.append('*');
                    }
                    start = i + 1;
                    curr = root;
                }
            } else {
                result.append(text.substring(start, i + 1));
                start = i + 1;
                curr = root;
            }
        }

        result.append(text.substring(start));
        return result.toString();
    }

    // 敏感词标记
    public static String markSensitiveWords(String text, Node root) {
        StringBuilder result = new StringBuilder();
        Node curr = root;
        int start = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (curr.getNext().containsKey(c)) {
                curr = curr.getNext().get(c);
                if (curr.isEnd()) {
                    result.append("<mark>");
                    result.append(text.substring(start, i + 1));
                    result.append("</mark>");
                    start = i + 1;
                    curr = root;
                }
            } else {
                result.append(text.substring(start, i + 1));
                start = i + 1;
                curr = root;
            }
        }

        result.append(text.substring(start));
        return result.toString();
    }
}