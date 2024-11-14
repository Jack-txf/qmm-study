package com.feng.chat.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnReadSysMsgDTO {
    private String nick;
    private String username;
    private String phone;

    private Long sysmsgId;
    private Long sendUser;
    private Integer msgType; // 0-好友申请  1-加入群聊申请  2-邀请加入群聊
    private String msgContent;
    private Long toUser;

    private Integer isAccept; // 接受么 0-待处理  1-接受  2-拒绝
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date createTime;
}
