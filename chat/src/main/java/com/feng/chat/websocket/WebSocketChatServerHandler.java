package com.feng.chat.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 比较原始的方法
@Component
@Slf4j
public class WebSocketChatServerHandler extends TextWebSocketHandler {
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // redis来判断token吧

    //全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
    // k:uid  v:uid的session
    private static final Map<Long, WebSocketSession> onlineSessions = new ConcurrentHashMap<>();

    // 连接建立后可进行的操作
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("到这了，handler里面");
        Map<String, Object> attributes = session.getAttributes();
        attributes.forEach((k, v)->{
            System.out.println("k:" + k + "  v:" + v );
        });
        String token = (String) attributes.get("token");
        String resStr;
        if ( token == null ) { // 第一道
            resStr = "您还未登录呢！";
            session.sendMessage(new TextMessage(resStr));
            session.close(); // 关闭
            return ;
        }
        String tokenStr = (String) redisTemplate.opsForValue().get(token); // 第二道
        System.out.println(tokenStr);
        if ( tokenStr == null ) {
            resStr = "您还未登录呢！";
            session.sendMessage(new TextMessage(resStr));
            session.close(); // 关闭
            return ;
        }
        String uidStr = tokenStr.substring(tokenStr.indexOf(":") + 1);
        if ( uidStr.isEmpty() ) {
            resStr = "您还未登录呢！";
            session.sendMessage(new TextMessage(resStr));
            session.close(); // 关闭
            return ;
        }
        Long uid = Long.valueOf(uidStr);
        if ( onlineSessions.containsKey(uid) ) { // 已经登录过了
            resStr = "您在别的设备已经登录过了！";
            session.sendMessage(new TextMessage(resStr));
            session.close(); // 关闭
        } else {
            onlineSessions.put(uid, session); // 加入连接
            resStr = "连接成功！";
            session.sendMessage(new TextMessage(resStr));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        InetSocketAddress address = session.getRemoteAddress();
        assert address != null;
        System.out.println(address.getHostName());
        System.out.println(message.getPayload());
    }

    // 连接关闭后可进行的操作
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Collection<WebSocketSession> values = onlineSessions.values();
        while( values.contains(session))
            values.remove(session);
        log.info("【当前人数】：{}, ", onlineSessions.size());
    }
}
