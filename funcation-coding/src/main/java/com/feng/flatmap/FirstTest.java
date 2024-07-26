package com.feng.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstTest {
    public static void main(String[] args) {
        //String[] words = new String[]{"Hello","World"};
        //List<String[]> a = Arrays.stream(words)
        //        .map(word -> word.split(""))
        //        .distinct()
        //        .collect(Collectors.toList());
        //a.forEach(System.out::print);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 4);
        List<Integer> list = integers
                .stream()
                .map(i -> i + 1)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("==========================");
        List<Integer> list1 = integers
                .stream()
                .flatMap(i -> Arrays.stream(new Integer[]{i + 1})).collect(Collectors.toList());
        list1.forEach(System.out::println);
    }

    public static void testFlatMap() {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);

        List<Integer> collect = list.stream()
                .flatMap(
                        t ->
                           t.stream().map(a -> a + 2)
                )
                .collect(Collectors.toList());
        System.out.println(collect);
        //运行结果 -------- [3, 4, 5, 6, 7, 8, 3, 4, 5, 6, 7, 8]
    }
}
