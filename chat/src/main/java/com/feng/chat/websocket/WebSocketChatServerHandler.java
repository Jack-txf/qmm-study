package com.feng.chat.websocket;

import com.feng.chat.mapper.ChatUserMapper;
import com.feng.chat.websocket.message.Message;
import com.feng.chat.websocket.message.MessageUtil;
import com.feng.chat.websocket.message.MsgType;
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
@Component(value = "webSocketChatServerHandler")
@Slf4j
public class WebSocketChatServerHandler extends TextWebSocketHandler {
    @Resource
    private ChatUserMapper chatUserMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // redis来判断token吧

    //全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
    // k:uid  v:uid的session
    private static final Map<Long, WebSocketSession> onlineSessions = new ConcurrentHashMap<>();

    public void userLogout( Long uid ) {
        onlineSessions.remove(uid);
        log.info("{} 【退出websocket连接】, 当前在线人数：{}", uid, onlineSessions.size());
    }

    // 连接建立后可进行的操作
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("【建立websocket连接】 {}", session);
        Map<String, Object> attributes = session.getAttributes();
        String token = (String) attributes.get("token");
        String resStr;
        if ( token == null ) { // 第一道
            session.sendMessage(new TextMessage(MessageUtil.failMessage("您还未登录呢！").toJsonMsg()));
            session.close(); // 关闭
            return ;
        }
        String tokenStr = (String) redisTemplate.opsForValue().get(token); // 第二道
        if ( tokenStr == null ) {
            session.sendMessage(new TextMessage(MessageUtil.failMessage("您还未登录呢！").toJsonMsg()));
            session.close(); // 关闭
            return ;
        }
        String uidStr = tokenStr.substring(tokenStr.indexOf(":") + 1);
        if ( uidStr.isEmpty() ) {
            session.sendMessage(new TextMessage(MessageUtil.failMessage("您还未登录呢！").toJsonMsg()));
            session.close(); // 关闭
            return ;
        }
        Long uid = Long.valueOf(uidStr);
        if ( onlineSessions.containsKey(uid) ) { // 已经登录过了
            session.sendMessage(new TextMessage(MessageUtil.failMessage("您在别的设备已经登录过了！").toJsonMsg()));
            session.close(); // 关闭
        } else {
            onlineSessions.put(uid, session); // 加入连接
            log.info(" 加入连接成功!【当前人数】：{}, ", onlineSessions.size());
            Message msg = new Message(MsgType.FLUSHFRIEND.getDescription());
            msg.setContent(chatUserMapper.selectFriends(uid));
            session.sendMessage(new TextMessage(msg.toJsonMsg()));
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

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("【连接出现了异常！现在断开此连接！】 {}", exception.getMessage());
        Collection<WebSocketSession> values = onlineSessions.values();
        while( values.contains(session))
            values.remove(session);
        log.info("【当前人数】：{}, ", onlineSessions.size());
    }
}
