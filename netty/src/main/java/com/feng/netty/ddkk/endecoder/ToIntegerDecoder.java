package com.feng.netty.ddkk.endecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
/*
解码器的主要职责是负责将入站数据从一种格式转换到另一种格式。Netty 提供了丰富的解码器抽象基类。方便开发者自定义解码器。
这些基类主要分为以下两类：
解码从字节到消息（ByteToMessageDecoder 和 ReplayingDecoder）。
解码从消息到消息（MessageToMessageDecoder）。
Netty 的解码器是ChannelInboundHandler的抽象实现。在实际应用中使用解码器很简单，就是将入站数据转换格式后传递到ChannelPipeline中的下一个ChannelInboundHandler进行处理。
将解码器放在ChannelPipeline中，会使整个程序变得灵活，同时也能方便重用逻辑。
 */

/*
每次从入站的ByteBuf读取4个字节，解码成整形，并添加到一个List中。当不能再添加数据的List时，它所包含的内容就会被发送到下一个ChannelInboundHandler。
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
   public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    if (in.readableBytes() >= 4) {
        out.add(in.readInt());
    } }
}