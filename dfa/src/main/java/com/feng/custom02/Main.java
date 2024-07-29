package com.feng.custom02;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 假设的敏感词集合
        Set<String> sensitiveWords = new HashSet<>();
        sensitiveWords.add("我是全世界最敏感的敏感词");
        sensitiveWords.add("敏感词2");

        Node root = SensitiveWordDFA.buildDFATree(sensitiveWords);

        String text = "这包含了我是全世界最敏感的敏感词和其他内容";

        // 敏感词过滤
        String filteredText = SensitiveWordDFA.filterSensitiveWords(text, root);
        System.out.println("过滤后的文本: " + filteredText);

        // 敏感词标记
        String markedText = SensitiveWordDFA.markSensitiveWords(text, root);
        System.out.println("标记后的文本: " + markedText);
    }
}