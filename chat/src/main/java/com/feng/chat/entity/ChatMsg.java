package com.feng.chat.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (ChatMsg)表实体类
 *
 * @author makejava
 * @since 2024-07-31 20:35:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("chat_msg")
public class ChatMsg {

    @TableId(type = IdType.AUTO)
    private String msgId;

    private Integer fromUser;

    private Integer toUser;

    private String msgDesc;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date modifyTime;
}

