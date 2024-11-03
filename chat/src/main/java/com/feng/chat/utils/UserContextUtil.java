package com.feng.chat.utils;

// 用户id上下文工具
public class UserContextUtil {
    private static final ThreadLocal<Long> local = new ThreadLocal<>();

    public static Long getUid() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }

    public static void set(Long uid) {
        local.set(uid);
    }
}
