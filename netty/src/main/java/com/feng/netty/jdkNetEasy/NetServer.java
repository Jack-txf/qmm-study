package com.feng.netty.jdkNetEasy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 网络服务器
 * @Author: txf
 * @Date: 2025/5/26
 */
public class NetServer {
    public static void main(String[] args) {
        // ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try ( ServerSocket socket = new ServerSocket(9999) ) {
            while ( true ) {
                Socket client = socket.accept();
                pool.execute(new Handler(client));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    private static class Handler implements Runnable {
        private Socket client;
        public Handler(Socket client) {
            this.client = client;
        }
        @Override
        public void run() {
            if ( client == null ) return;
            try {
                while ( true ) {
                    byte[] bytes = new byte[256];
                    int read = client.getInputStream().read(bytes);
                    if ( read != -1 ) {
                        String message = new String(bytes, 0, read);
                        if ( "bye".equals(message) ) break; // 断开连接
                        System.out.println("客户端发来消息：" + message);
                        client.getOutputStream().write("hello, 我是服务器".getBytes());
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if ( client != null ) client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
