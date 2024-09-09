package com.feng.netty.day2.chatdemo.server.handler;

import com.feng.netty.day2.chatdemo.message.GroupMembersRequestMessage;
import com.feng.netty.day2.chatdemo.message.GroupMembersResponseMessage;
import com.feng.netty.day2.chatdemo.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

@ChannelHandler.Sharable
public class GetGroupMembersMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {
    // gmembers [group name]
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage msg) throws Exception {
        Set<String> members = GroupSessionFactory.getGroupSession()
                .getMembers(msg.getGroupName());
        ctx.writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
