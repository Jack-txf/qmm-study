package com.feng.chat.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// 比较原始的方法
@Component
@ServerEndpoint("/txfChat/{token}")
@Slf4j
public class WebSocketChatServer {
    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static final Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token")String token) {
        if ( !onlineSessions.containsKey(token) ) {
            onlineSessions.put(token, session);
            // 向所有人发一个消息
            sendMessageToAll("【系统消息】:" + token + "上线了!!~");
            System.out.println("当前在线人数：" + onlineSessions.size());
        }
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     */
    @OnMessage // 服务器接收到消息的时候
    public void onMessage(Session session, String jsonStr) {
        System.out.println(session);
        System.out.println("收到消息：" + jsonStr);
        sendMessageToAllExcludeMe(session, jsonStr);
    }

    private void sendMessageToAllExcludeMe(Session session, String jsonStr) {
        String userToken = onlineSessions.entrySet()
                .stream().filter(item -> item.getValue() == session)
                .map(Map.Entry::getKey).collect(Collectors.joining());
        String msg = "{ "+ userToken +" }：" + jsonStr;
        onlineSessions.forEach((id, session1) -> {
            try {
                if ( session != session1) { // 如果不是自己
                    session1.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        Collection<Session> values = onlineSessions.values();
        while( values.contains(session))
            values.remove(session);
        System.out.println("当前人数：" + onlineSessions.size());
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给所有人
     */
    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
