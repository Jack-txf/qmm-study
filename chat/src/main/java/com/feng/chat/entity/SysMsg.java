package com.feng.chat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_msg_tab")
@Builder
public class SysMsg {
    @TableId(type = IdType.AUTO)
    private Long sysmsgId;
    private Long sendUser;
    private Integer msgType; // 0-好友申请  1-加入群聊申请  2-邀请加入群聊
    private Long toUser;
    private Boolean isRead; // toUser读过没有

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date modifyTime;
}
