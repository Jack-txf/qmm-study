package com.feng.netty.ddkk.endecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/*
编码器就是用来把出站（针对本身来讲，发送都是出站，接收都是入站，不管是客户端还是服务端）数据从一种格式转换到另外一种格式，因此它实现了ChannelOutboundHandler

在MessageToByteEncoder抽象类中，唯一要关注的是encode方法，该方法是开发者需要实现的唯一抽象方法。它与出站消息一起调用，
将消息编码为ByteBuf，然后，将ByteBuf转发到ChannelPipeline中的下一个ChannelOutboundHandler。
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);// 将Short转成二进制字节流写入ByteBuf中
    }

}