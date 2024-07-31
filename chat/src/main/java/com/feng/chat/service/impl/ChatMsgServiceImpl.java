package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.mapper.ChatMsgMapper;
import com.feng.chat.entity.ChatMsg;
import com.feng.chat.service.ChatMsgService;
import org.springframework.stereotype.Service;

/**
 * (ChatMsg)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 20:35:30
 */
@Service("chatMsgService")
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

}

