package com.feng.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexExample {
    public static void main(String[] args) {
        // 创建一个包含多个班级的学校对象
        Classroom class1 = new Classroom(Arrays.asList(new Student("Alice"), new Student("Bob")));
        Classroom class2 = new Classroom(Arrays.asList(new Student("Charlie"), new Student("David")));
        School school = new School(Arrays.asList(class1, class2));

        long start = System.currentTimeMillis();
        List<Classroom> classrooms = school.getClassrooms();
        String[] names = new String[4];
        int i = 0;
        for (Classroom classroom : classrooms) {
            List<Student> students = classroom.getStudents();
            for (Student student : students) {
                names[i++] = student.getName();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("循环处理 耗时：" + (end - start));
        System.out.println("循环处理：" + names);  // 输出: [Alice, Bob, Charlie, David]


        long start1 = System.currentTimeMillis();
        // 使用 map 和 flatMap 找到所有学生的姓名，并合并成一个列表
        List<String> allStudentNames = school.getClassrooms().stream()
                .flatMap(classroom -> classroom.getStudents().stream())
                .map(Student::getName)
                .collect(Collectors.toList());
        long end1 = System.currentTimeMillis();

        List<Stream<String>> collect = school.getClassrooms().stream()
                .map(classroom ->
                      classroom.getStudents()
                      .stream()
                      .map(Student::getName)
                )
                .collect(Collectors.toList());


        System.out.println("流式处理 耗时：" + (end1 - start1));
        System.out.println("流式处理：" + allStudentNames);  // 输出: [Alice, Bob, Charlie, David]
    }
}
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Classroom {
    private List<Student> students;

    public Classroom(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}

class School {
    private List<Classroom> classrooms;

    public School(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }
}
