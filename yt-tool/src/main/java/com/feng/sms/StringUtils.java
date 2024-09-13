package com.feng.sms;

import com.feng.sms.pojo.Student;

import java.util.Arrays;
import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/9/13
 */
public class StringUtils {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student("田小锋", "123456"),
                new Student("陈康乐", "123456"),new Student("刘成阿萨大大撒凯", "123456"));
        for ( int i = 0; i < list.size(); ++i ) {
            String name = list.get(i).getName();
            list.get(i).setName(fun(name));
        }

        list.forEach(System.out::println);
    }

    public static String fun( String name ) {
        if ( name == null || "".equals(name) ) return name;
        int l = 0, r = name.length()-1;
        StringBuilder builder = new StringBuilder(name);
        for ( int i = l+1; i < r; ++i ) builder.setCharAt(i, '*');
        return builder.toString();
    }
}
