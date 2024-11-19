package com.feng.chat.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    @TableId(type = IdType.ASSIGN_ID) // 雪花算法
    private String msgId; // 消息id

    private Long fromUser;

    private Long toUser;

    private String msgDesc;

    private Boolean isRead; // 阅读没有

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date modifyTime;
}

