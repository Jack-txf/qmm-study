package com.feng.chat.websocket.message;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseMessage implements Serializable {

    private static final long serialVersionUID = -1L;

    private String type;
    private Object content;
    public BaseMessage() {
    }
    public BaseMessage( String type) {
        this.type = type;
    }

    public String toJsonMsg() {
        return JSONUtil.toJsonStr(this);
    }
}
