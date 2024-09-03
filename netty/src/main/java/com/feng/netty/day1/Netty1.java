package com.feng.netty.day1;

import io.netty.channel.DefaultEventLoopGroup;

public class Netty1 {
    public static void main(String[] args) {
        // 内部创建了两个 EventLoop, 每个 EventLoop 维护一个线程
        DefaultEventLoopGroup group = new DefaultEventLoopGroup(2);
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
    }
}
