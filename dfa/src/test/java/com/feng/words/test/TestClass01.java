package com.feng.words.test;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.jupiter.api.Test;

public class TestClass01 {


    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        String str = "字体印刷也太他妈的模糊了把，风流少女，龟头狠狠插入，直接射精";
        //boolean contains = SensitiveWordHelper.contains(str);
        //System.out.println(contains);
        String replace = SensitiveWordHelper.replace(str, '*');
        System.out.println(replace);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        //String word = SensitiveWordHelper.findFirst(str);
        //System.out.println(word);

        //List<String> strings = SensitiveWordHelper.findAll(str);
        //strings.forEach(System.out::println);
    }
}
