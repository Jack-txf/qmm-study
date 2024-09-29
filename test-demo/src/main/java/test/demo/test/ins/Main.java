package test.demo.test.ins;

import test.demo.test.ins.A;
import test.demo.test.ins.AA;
import test.demo.test.ins.B;

public class Main {
    public static void main(String[] args) {
        AA aa = new AA();
        System.out.println(aa instanceof A);

        B b = new B();
        System.out.println(b instanceof AA);

        fun1(aa);

        fun(aa);
    }

    public static <T extends Main> T fun(T t) {
        System.out.println("balabala");
        return t;
    }

    public static <T> T fun1(T t) {
        System.out.println("balabala");
        return t;
    }
}
