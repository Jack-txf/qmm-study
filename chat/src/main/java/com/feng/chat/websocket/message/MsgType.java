package com.feng.chat.websocket.message;

import lombok.Getter;

@Getter
public enum MsgType {
    FLUSHFRIEND("flushFriend"),
    SYSTEM("system");
    private final String description;
    MsgType(String description) {
        this.description = description; // 初始化描述信息
    }

    public static MsgType fromDescription(String description) {
        for (MsgType type : MsgType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("没有这个枚举" + description);
    }

    @Override
    public String toString() {
        return description; // 或者返回其他你想要的字符串表示
    }
}
