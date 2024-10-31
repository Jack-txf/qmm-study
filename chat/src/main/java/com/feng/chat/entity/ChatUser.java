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
 * (ChatUser)表实体类
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("chat_user")
public class ChatUser{

    @TableId(type = IdType.AUTO)
    private Long uid;

    private String username;

    private String phone;

    private String password;

    private String nick;

}

