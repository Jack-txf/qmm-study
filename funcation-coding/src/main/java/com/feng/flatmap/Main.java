package com.feng.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<List<Integer>> ans = new ArrayList<>();
        for ( int i = 1; i <= 5; ++i ) {
            List<Integer> integers = Arrays.asList(i + 1, i + 2, i + 3);
            ans.add(integers);
        }

        List<Integer> integers = ans.stream()
                .flatMap(Collection::stream)
                .map(i -> i * 10)
                .collect(Collectors.toList());

        for (Integer integer : integers) {
            System.out.printf("%5d ", integer);
        }
    }
}