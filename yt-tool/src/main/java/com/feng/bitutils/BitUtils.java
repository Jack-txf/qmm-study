package com.feng.bitutils;

import java.util.*;

/*
 五位二进制: 1        1       1     1       1
       报名人住址   身份证号  年 龄  手机号   姓名
  从右往左： 第0位，第1位，第2位.....
  如果报名必填项只有手机号和姓名：00011(十进制3)， 如果还需要身份证号: 01011(十进制11)
 */
public class BitUtils {
    public static final Map<String, Integer> w = new HashMap<>();
    public static final Map<Integer, String> v = new HashMap<>();
    public static int MAXN = -1;
    static {
        w.put("姓名", 0); w.put("手机号", 1); w.put("年龄", 2);
        w.put("身份证号", 3); w.put("住址", 4);

        v.put(0, "姓名"); v.put(1, "手机号"); v.put(2, "年龄");
        v.put(3, "身份证号"); v.put(4, "住址");

        MAXN = (1 << w.size()) - 1;
    }

    // 获取存在数据库中的int值，前端传过来“报名必填项”的字符串数组
    public static int getRequiredItemValue(List<String> requiredStrs) {
        int requiredItemValue = 0;
        if ( requiredStrs != null && requiredStrs.size() > 0 ) {
            for (String str : requiredStrs) {
                if ( !w.containsKey(str) ) throw new RuntimeException( "必填项字符串有误!【" + str + "】");
                int i = w.get(str);
                requiredItemValue |= (1<<i);
            }
        }
        return requiredItemValue;
    }

    // 根据requireItem的值，获取需要的必填项
    public static List<String> getRequiredItemStrings( int requiredItemValue ) {
        if ( requiredItemValue > MAXN ) throw new RuntimeException( "requiredItemValue数值过大，没有这么多必填项");
        List<String> requiredStrs = new ArrayList<>();
        int i = 0;
        while ( requiredItemValue  > 0 ) {
            if ( (requiredItemValue & 1) == 1 ) requiredStrs.add(v.get(i));
            requiredItemValue >>= 1;
            ++i;
        }
        return requiredStrs;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("手机号", "姓名", "住址");
        int requiredItemValue = getRequiredItemValue(strings);
        System.out.println(requiredItemValue);

        System.out.println(getRequiredItemStrings(31));

    }
}