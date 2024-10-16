package com;

import org.junit.platform.engine.discovery.PackageSelector;

import java.io.*;
import java.util.*;

/**
 * @author Williams_Tian
 * @CreateDate 2024/9/2
 */
public class test {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\xinhua\\Desktop\\book.txt";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        List<String> bookIds = new ArrayList<>();
        String bookId = "";
        while ( (bookId = reader.readLine()) != null ) {
            bookIds.add(bookId);
        }

        // 把这些书 序列化后放入文本文件中
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bookIds.size(); i++) {
            if ( i != 0 ) s.append(",").append("'").append(bookIds.get(i)).append("'");
            else s.append("'").append(bookIds.get(i)).append("'");
        }
        System.out.println(s);

        reader.close();
        //try {
        //    test1();
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    }

    public static void test1() throws IOException, IndexOutOfBoundsException {
        // Random random = new Random();
        // int i = random.nextInt(2);
        // if (i == 1) throw new IOException("qqqqq");
        // throw new IndexOutOfBoundsException("123456");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
