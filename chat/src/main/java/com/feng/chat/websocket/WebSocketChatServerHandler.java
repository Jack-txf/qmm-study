package com.feng.chat.websocket;

import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.vo.UnReadSysMsgVo;
import com.feng.chat.mapper.ChatUserMapper;
import com.feng.chat.mapper.SysmsgMapper;
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
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// 比较原始的方法
@Component(value = "webSocketChatServerHandler")
@Slf4j
public class WebSocketChatServerHandler extends TextWebSocketHandler {
    @Resource
    private ChatUserMapper chatUserMapper;
    @Resource
    private SysmsgMapper sysmsgMapper;

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
            // 发送一个刷新好友列表的消息
            Message msg = new Message(MsgType.FLUSHFRIEND.getDescription());
            msg.setContent(chatUserMapper.selectFriends(uid));
            session.sendMessage(new TextMessage(msg.toJsonMsg()));
            // 再给该用户发送一条系统未读消息的个数
            List<UnReadSysMsgVo> unReadSysMsgs = sysmsgMapper.selectNeedReadMsg(uid); // 查出未读消息
            if ( unReadSysMsgs != null && !unReadSysMsgs.isEmpty()) {
                Message msg2 = new Message(MsgType.SYSTEM_BADGE.getDescription());
                msg2.setContent(unReadSysMsgs.size());
                session.sendMessage(new TextMessage(msg2.toJsonMsg()));
            }
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

    // 给用户发送系统消息未读数
    public void sendSysMsgToUser(SysMsg sysMsg) {
        Long toUser = sysMsg.getToUser(); // 给这个人发型消息
        Set<Long> uids = onlineSessions.keySet();
        for (Long uid : uids) {
            if ( toUser.equals(uid) ) { // 如果这个人在线
                log.info("【添加好友的系统消息】{}  +  {}", sysMsg.getSendUser(), sysMsg.getToUser());
                WebSocketSession session = onlineSessions.get(toUser);
                // 从数据库里面查出toUser的未读消息
                List<UnReadSysMsgVo> sysmsgs = sysmsgMapper.selectNeedReadMsg(toUser);
                if ( sysmsgs != null && !sysmsgs.isEmpty() ) {
                    try {
                        session.sendMessage(new TextMessage(MessageUtil.unReadSysMsg(sysmsgs)));
                    } catch (IOException e) {
                        log.info("发送消息出现了异常！{}", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
