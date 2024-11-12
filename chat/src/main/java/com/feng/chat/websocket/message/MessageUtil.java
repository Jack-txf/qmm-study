package com.feng.chat.websocket.message;

import com.feng.chat.entity.vo.UnReadSysMsgVo;

import java.util.List;

public class MessageUtil {
    public static Message failMessage( String msg ) {
        return new Message(MsgType.FAIL.getDescription(), msg);
    }

    public static String unReadSysMsg(List<UnReadSysMsgVo> sysmsgs) {
        // 构建未处理消息
        Message message = new Message(MsgType.SYSTEM.getDescription(), sysmsgs); // 系统类型的消息
        return message.toJsonMsg();
    }
}
