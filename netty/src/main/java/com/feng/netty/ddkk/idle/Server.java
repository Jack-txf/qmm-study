package com.feng.netty.ddkk.idle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ServerBootstrap server = bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向 pipeline 加入一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向 pipeline 加入一个编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new IdleStateHandler(10, 20, 60, TimeUnit.SECONDS));
                            pipeline.addLast(new ServerHandler());
                        }
                    });
            ChannelFuture future = server.bind(9555).sync();
            future.channel().closeFuture().sync();
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
