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
    }
}
