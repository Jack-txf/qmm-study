package com.feng.chat.websocket.message;


import lombok.Data;

import java.io.Serializable;

@Data
public class FlushFriendListMsg extends BaseMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    public FlushFriendListMsg() {
        super(MsgType.FLUSHFRIEND.getDescription());
    }

}
