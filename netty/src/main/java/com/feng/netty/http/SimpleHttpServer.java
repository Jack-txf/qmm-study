package com.feng.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class SimpleHttpServer {
    public void start(String host, int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel c) {
                            c.pipeline().addLast(new HttpRequestDecoder()) // 请求解码，入站
                                    .addLast(new HttpResponseEncoder()) // 响应编码，出站
                                    .addLast(new HttpObjectAggregator(512 * 1024))
                                    .addLast(new SimpleHttpServerHandler());
                        }
                    });
            ChannelFuture channelFuture = server.bind(host, port).sync();

            channelFuture.channel().closeFuture().sync();
        } catch ( InterruptedException e ) {
            System.out.println(" 出问题了!");
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new SimpleHttpServer().start("127.0.0.1", 8080);
    }
}
