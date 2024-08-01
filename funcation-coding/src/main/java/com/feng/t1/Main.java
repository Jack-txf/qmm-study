package com.feng.t1;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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

        double v1 = 2 * 1.0 / 3;
        String s = String.format("%.2f", v1);
        System.out.println(s

        );
    }


}
