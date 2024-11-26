package com.feng.netty.ddkk.reactormodel.client;

public class ClientDemo {

    public static void main(String[] args) {
        new Thread(new NIOClient("127.0.0.1", 2333)).start();
        new Thread(new NIOClient("127.0.0.1", 2333)).start();
    }

}