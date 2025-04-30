package com.feng.tools;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author
 * @Date 2025/4/30 14:16
 * @注释
 */
public class TMap {
    public static void main(String[] args) {
        testConcurrentHashMap();
    }

    public static void testConcurrentHashMap() {
        ConcurrentHashMap<String, Object> m = new ConcurrentHashMap<>();
        m.put("1", "##");
        printMap(m);
        m.put("1", "2");
        printMap(m);
        //===================================== 【1】putIfAbsent
        m.putIfAbsent("2", "3");
        m.putIfAbsent("1", "3"); // 这一个并没有覆盖原来的喔
        printMap(m);
        //===================================== 【2】compute
        // 把key为"1"的value变成 k + v + "hahah"
        m.compute("1", (k, v) -> k + v + "hahah");
        // key不存在，则添加 --- value就变成: 3nullhahah
        m.compute("3", (k, v) -> k + v + "hahah");
        printMap(m);
        //===================================== 【3】merge
        m.merge("1", "3ppp", (oldVal, newVal) -> oldVal.toString() + newVal); // oldVal:旧值 newVal:新值[传进去的value]
        m.merge("4", "3", (oldVal, newVal) -> "hahah" + oldVal + newVal);
        printMap(m);
    }

    private static void printMap( ConcurrentHashMap<String, Object> m ) {
        System.out.println("=====================");
        for (String key : m.keySet()) {
            System.out.println(key + ":" + m.get(key));
        }
        System.out.println("=====================");
    }
}
