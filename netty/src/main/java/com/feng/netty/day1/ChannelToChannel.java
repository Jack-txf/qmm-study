package com.feng.netty.day1;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;

public class ChannelToChannel {
    public static void main(String[] args) throws IOException {
        URL url1 = ChannelToChannel.class.getClassLoader().getResource("ch1.txt");
        String file1 = url1.getFile();
        System.err.println(file1);

        URL url2 = ChannelToChannel.class.getClassLoader().getResource("ch2.txt"); // 写在了在生成的target里面
        String file2 = url2.getFile();
        System.err.println(file2);

        FileInputStream in = new FileInputStream(file1);
        FileOutputStream out = new FileOutputStream(file2);

        FileChannel channel1 = in.getChannel();
        FileChannel channel2 = out.getChannel();
        //channel1.force(true);
        //channel2.force(true);

        channel1.transferTo(0, channel1.size(), channel2);

        in.close();
        out.close();

    }
}
