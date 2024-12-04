package com.feng.intro;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQue {
    public static void main(String[] args) {

        //创建优先级队列
        PriorityQueue<Integer> numbers = new PriorityQueue<>(new CustomComparator());
        numbers.add(4);
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        while ( !numbers.isEmpty() ) {
            Integer peek = numbers.peek();
            System.out.println(peek);
            numbers.poll();
        }
    }
}
class CustomComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer number1, Integer number2) {
        int value =  number1.compareTo(number2);
        //元素以相反的顺序排序
        if (value > 0) {
            return -1;
        }
        else if (value < 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
