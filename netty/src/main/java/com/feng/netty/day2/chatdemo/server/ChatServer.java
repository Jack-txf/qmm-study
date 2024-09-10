package com.feng.netty.day2.chatdemo.server;

import com.feng.netty.day2.chatdemo.message.LoginRequestMessage;
import com.feng.netty.day2.chatdemo.message.LoginResponseMessage;
import com.feng.netty.day2.chatdemo.protocol.MessageCodecSharable;
import com.feng.netty.day2.chatdemo.protocol.ProcotolFrameDecoder;
import com.feng.netty.day2.chatdemo.server.handler.*;
import com.feng.netty.day2.chatdemo.server.service.UserServiceFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG); // 日志
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable(); // 消息编码器

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_CODEC); // 消息编解码

                    // ch.pipeline().addLast(new PingMessageHandler());// ping
                    ch.pipeline().addLast(new LoginRequestMessageHandler());// 处理登录消息
                    // ch.pipeline().addLast(new SingleChatMessageHandler());// 处理单聊消息
                    // // ========= 群聊
                    // ch.pipeline().addLast(new GroupCreateMessageHandler()); // 创建群聊消息
                    // ch.pipeline().addLast(new GroupJoinMessageHandler());// 加入群聊
                    // ch.pipeline().addLast(new GetGroupMembersMessageHandler()); // 查看群成员
                    // ch.pipeline().addLast(new QuitGroupMessageHandler()); // 退出群聊
                    // ch.pipeline().addLast(new GroupChatMessageHandler()); // 发送群聊消息
                    // ========
                    // 退出聊天室系统
                    // ch.pipeline().addLast(new QuitHandler()); // 退出，断开连接

                }
            });
            Channel channel = serverBootstrap.bind(9009).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
