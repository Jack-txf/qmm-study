package com.feng.chat.websocket.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSendMsg {
    private String type; // 消息类型
    private String content;
    private Long toUser;
    private Long toGroup;
    private String username; // 发送者username
}
