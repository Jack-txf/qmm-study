package com.feng.netty.ddkk.endecoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

//将 Integer 解码为 Long，然后将 Long 编码为 Integer。
public class NumberCodec extends MessageToMessageCodec<Integer,Long> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
            out.add(msg.longValue());
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
            out.add(msg.intValue());
    }
}
