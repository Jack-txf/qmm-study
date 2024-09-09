package com.feng.netty.day2.chatdemo.server.handler;

import com.feng.netty.day2.chatdemo.message.ChatRequestMessage;
import com.feng.netty.day2.chatdemo.message.ChatResponseMessage;
import com.feng.netty.day2.chatdemo.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class SingleChatMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String toUser = msg.getTo();
        Channel toChannel = SessionFactory.getSession().getChannel(toUser);
        // 在线
        if (toChannel != null) {
            toChannel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        }
        // 不在线
        else {
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或者不在线"));
        }
    }
}
