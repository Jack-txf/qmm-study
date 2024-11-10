package com.feng.chat.websocket.message;

public class MessageUtil {
    public static Message failMessage( String msg ) {
        return new Message(MsgType.FAIL.getDescription(), msg);
    }
}
