package com.feng.chat.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
}

