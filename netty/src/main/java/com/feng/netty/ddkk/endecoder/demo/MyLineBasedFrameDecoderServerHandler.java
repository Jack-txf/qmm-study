package com.feng.netty.ddkk.endecoder.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyLineBasedFrameDecoderServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 接收msg消息，此处已经无需解码了
        System.out.println("Client -> Server: " + msg);
    }
}