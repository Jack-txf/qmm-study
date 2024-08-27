package com.feng;

import com.feng.res.ResourceResolver;
import com.feng.t1.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/16
 */
public class Main {
    public static void main(String[] args) {
        // ResourceResolver resolver = new ResourceResolver("com.feng");
        // List<Object> list = resolver.scan(resource -> {
        //     return null;
        // });
        Student me1 = new Student(1, "me");
        Student me2 = new Student(4, "me");
        Student me3 = new Student(5, "qwrr");
        Student me4 = new Student(2, "23232");
        Student me5 = new Student(2, "qwer");

        ArrayList<Student> stus = new ArrayList<>();
        stus.add(me1);stus.add(me2);stus.add(me3);stus.add(me4);stus.add(me5);
        // stus.sort(Comparator.comparing(Student::getId));
        // stus.forEach(System.out::println);

        Set<Student> students = new LinkedHashSet<>(stus);
        ArrayList<Student> list = new ArrayList<>(students);


        // List<Student> list = stus.stream().distinct().collect(Collectors.toList());

        list.forEach(System.out::println);
    }
}
