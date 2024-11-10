package com.feng.chat.websocket.message;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = -1L;

    private String type;
    private Object content;
    public Message() {
    }
    public Message( String type) {
        this.type = type;
    }

    public String toJsonMsg() {
        return JSONUtil.toJsonStr(this);
    }
}
