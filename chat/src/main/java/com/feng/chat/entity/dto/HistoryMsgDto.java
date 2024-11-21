package com.feng.chat.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryMsgDto {
    private Long fromUser;
    private String fromNick;
    private Long toUser;
    private String msgDesc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
}
