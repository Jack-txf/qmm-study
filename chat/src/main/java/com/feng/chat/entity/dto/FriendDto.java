package com.feng.chat.entity.dto;

import lombok.Data;

@Data
public class FriendDto {
    private Long uid;
    private String nick;
    private String lastMessage;
}
