package com.feng.custom01;

public class App {
    public static void main(String[] args) {
        String content = "我觉得今天还行。";
        // 定义敏感词库 后续可以对接数据库
        String[] sensitiveWords = {"今天", "今天很好", "今天真烦"};

        long st1 = System.currentTimeMillis();
        // 设置敏感词库
        Finder.addSensitiveWords(sensitiveWords);
        long en1 = System.currentTimeMillis();
        System.out.println("加载词库耗时：" + (en1 - st1));

        System.out.println("======================");

        long st2 = System.currentTimeMillis();
        // 敏感词过滤替换字符为*
        String replace = Finder.filter(content);
        // 输出结果：我觉得**还行。
        System.out.println(replace);
        long en2 = System.currentTimeMillis();
        System.out.println("匹配耗时：" + (en2 - st2));


        System.out.println("======================");
        System.out.println("======================");
        Finder.printTree(Finder.getTree(), 0);
    }
}
