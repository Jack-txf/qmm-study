package com.feng.lqb10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LQ19697 {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\compte\\files\\jwt.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Item> items = new ArrayList<>();
        String line = null;
        while ( (line = reader.readLine()) != null ) {
            String[] s = line.split(" ");
            items.add(new Item(s[0].charAt(0), s[1].charAt(0), Long.parseLong(s[2])));
        }

        // items数组根据时间戳从小到大排个序
        items.sort((o1, o2) -> (int) (o1.getTime() - o2.getTime()));

        int ans = 1, res = 0;
        for (int i = 0; i < items.size()-1; i++) {
            if ( items.get(i+1).getTime() - items.get(i).getTime() <= 1000
                    && items.get(i).getRight() == items.get(i).getKeyDown()
                    && items.get(i+1).getRight() == items.get(i+1).getKeyDown()) {
                ans++;
                res = Math.max(ans, res);
            } else {
                ans = 1;
            }
        }
        System.out.println(res);
    }
}

class Item {
    private char right;
    private char keyDown;
    private long time;

    public Item(char right, char keyDown, long time) {
        this.right = right;
        this.keyDown = keyDown;
        this.time = time;
    }

    public char getRight() {
        return right;
    }

    public void setRight(char right) {
        this.right = right;
    }

    public char getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(char keyDown) {
        this.keyDown = keyDown;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
