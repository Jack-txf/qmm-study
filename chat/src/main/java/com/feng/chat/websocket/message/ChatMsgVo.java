package com.feng.chat.websocket.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMsgVo {
    private Long uid;
    private String lastMessage;
}
