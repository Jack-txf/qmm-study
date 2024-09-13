package com.feng.chat.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;

@ChannelHandler.Sharable
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        SocketAddress address = ctx.channel().remoteAddress();
        System.out.println("【收到 " + address + " 的消息】：" + msg);

        ctx.writeAndFlush("hello world");
    }
}
