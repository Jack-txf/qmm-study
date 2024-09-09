package com.feng.netty.day2.chatdemo.server.handler;

import com.feng.netty.day2.chatdemo.message.PingMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class PingMessageHandler extends SimpleChannelInboundHandler<PingMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PingMessage msg) throws Exception {
        // ping -- 返回一个pong
        System.err.println("ping: " + msg);
        ctx.writeAndFlush("pong");
    }
}
