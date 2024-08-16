package com.feng.t1;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Williams_Tian
 * @CreateDate 2024/7/30
 */
public class Main {
    public static void main(String[] args) {
        // List<Student> students = Arrays.asList(new Student(1, "1111"), new Student(2, "2222"));
        // List<Integer> integers = students.stream().distinct()
        //         .map(Student::getId).collect(Collectors.toList());
        //
        // for (Student student : students) {
        //     System.out.println(student);
        // }
        // LocalDate now = LocalDate.now();
        // System.out.println(now.getYear());
        // System.out.println(now.getMonthValue());
        // Month month = now.getMonth();
        //
        // String s = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        // System.out.println(s);

        // double v1 = 2 * 1.0 / 3;
        // String s = String.format("%.2f", v1);
        // System.out.println(s

        // Date date = new Date();
        // String str = "https://yuetao-file.oss-cn-chengdu.aliyuncs.com/goodscomment/2024/8/1d74b7a19fa750000024.png";
        // int i = str.indexOf("goodscomment");
        // String substring = str.substring(i);
        // System.out.println(substring);

        // List<Student> list = getList();
        // assert list != null;
        // List<String> collect = list.stream().map(Student::getName).distinct().collect(Collectors.toList());
        //
        // collect.forEach(System.out::println);

        // ArrayList<Integer> integers = new ArrayList<>();
        // integers.add(1);
        //
        // ArrayList<Integer> integers1 = new ArrayList<>();
        // integers1.add(2); integers1.add(3);
        //
        // integers.addAll(integers1);
        // integers.forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 2, 3, 9, 10, -1, 1, 2);
        integers = integers.stream().distinct().collect(Collectors.toList());
        System.out.println(integers);
    }
    private static List<Student> getList() {
        return null;
    }


}
