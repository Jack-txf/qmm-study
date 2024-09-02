package com;

import org.junit.platform.engine.discovery.PackageSelector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Williams_Tian
 * @CreateDate 2024/9/2
 */
public class test {
    public static void main(String[] args) {
        try {
            test1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test1() throws IOException, IndexOutOfBoundsException {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1) throw new IOException("qqqqq");
        throw new IndexOutOfBoundsException("123456");
    }
}
