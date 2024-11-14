package com.feng.chat.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnReadSysMsgVo {
    private Long sysmsgId;
    private String nick;
    private String username;
    private Date createTime;
    private Integer msgType;
    private String phone;
    private Long toUser;
}
