package com.feng.netty.day1.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class Client2 {
    // 连接的是n1包下的Server
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("127.0.0.1", 8080);

        // 方式1. connect 方法是异步的，意味着不等连接建立，方法执行就返回了。因此 channelFuture 对象中不能【立刻】获得到正确的 Channel 对象
        // System.err.println(channelFuture.channel()); // 1
        // channelFuture.sync(); // 2  -- sync 方法是同步等待连接建立完成
        // System.err.println(channelFuture.channel()); // 3

        // 方式2.添加回调
        channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            System.err.println(channelFuture.channel());
        });

    }
}
