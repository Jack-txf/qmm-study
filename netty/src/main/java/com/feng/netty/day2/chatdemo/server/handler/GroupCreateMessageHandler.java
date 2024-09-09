package com.feng.netty.day2.chatdemo.server.handler;

import com.feng.netty.day2.chatdemo.message.GroupCreateRequestMessage;
import com.feng.netty.day2.chatdemo.message.GroupCreateResponseMessage;
import com.feng.netty.day2.chatdemo.server.session.Group;
import com.feng.netty.day2.chatdemo.server.session.GroupSession;
import com.feng.netty.day2.chatdemo.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

@ChannelHandler.Sharable
public class GroupCreateMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    // gcreate [group name] [m1,m2,m3...]
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();

        Channel myslef = ctx.channel();
        // 群管理器
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group == null) {
            // 返回响应成功消息
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
            // 发送拉群消息
            List<Channel> channels = groupSession.getMembersChannel(groupName); // 根据群组的名字拿到所有人的channel
            for (Channel channel : channels) {
                if ( channel != myslef)
                    channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入" + groupName));
            }
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }
    }
}
