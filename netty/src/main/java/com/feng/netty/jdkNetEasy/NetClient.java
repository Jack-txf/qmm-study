package com.feng.netty.jdkNetEasy;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description: 网络客户端
 * @Author: txf
 * @Date: 2025/5/26
 */
public class NetClient {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try ( Socket client = new Socket("localhost", 9999) ) {
            // 第一个线程写东西给服务器
            Thread w = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.nextLine();
                        client.getOutputStream().write(msg.getBytes());
                        client.getOutputStream().flush();
                        if ( "bye".equals(msg) ) break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            w.start();

            // 第二个线程读取服务器发来的消息
            Thread r = new Thread(() -> {
                while (true) {
                    try {
                        byte[] bytes = new byte[256];
                        int read = client.getInputStream().read(bytes);
                        if ( read == -1 ) break;
                        System.out.println("【服务器】:" + new String(bytes, 0, read));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            r.start();

            w.join(); r.join();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            in.close();
        }
    }
}
