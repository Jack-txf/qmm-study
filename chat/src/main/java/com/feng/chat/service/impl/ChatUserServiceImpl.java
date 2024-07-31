package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.mapper.ChatUserMapper;
import com.feng.chat.service.ChatUserService;
import org.springframework.stereotype.Service;

/**
 * (ChatUser)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@Service("chatUserService")
public class ChatUserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser> implements ChatUserService {

}

