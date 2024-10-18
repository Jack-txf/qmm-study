package test.demo.test.list;

import java.util.ArrayList;
import java.util.List;

public class TestListClear {
    public static void main(String[] args) {
        List<Student> students = createList();
        int batch = 33;
        ArrayList<Student> students1 = new ArrayList<>();
        for ( int i = 0; i < students.size(); ++i ) {
            if ( students1.size() >= batch) {
                System.err.println("执行插入++======== start");
                System.err.println("原数组：");
                students.forEach(System.err::println);
                System.err.println("临时数组：");
                students1.forEach(System.err::println);
                System.err.println("执行插入+ 结束 +======== end");

                students1.clear();
            }
            students1.add(students.get(i));
        }

        if ( students1.size() > 0 ) {
            System.err.println("执行插入++======== start");
            System.err.println("原数组：");
            students.forEach(System.err::println);
            System.err.println("临时数组：");
            students1.forEach(System.err::println);
            System.err.println("执行插入+ 结束 +======== end");
        }
    }

    public static List<Student> createList() {
        ArrayList<Student> students = new ArrayList<>();
        for ( int i = 0; i < 100; ++i ) students.add(new Student(i, "asda" + i, "哈哈" + i));
        return students;
    }
}
