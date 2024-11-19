package com.feng.chat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NormalMsgDto {
    @NotBlank
    private Long fromUser;

    @NotBlank
    private Long toUser;

    @NotBlank
    private String msgDesc; // 消息内容
}
