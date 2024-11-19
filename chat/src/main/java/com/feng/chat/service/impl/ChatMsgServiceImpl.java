package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.entity.dto.NormalMsgDto;
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
    /* 1. 发送普通消息( 单人对单人 )
        方案一：先存入redis，然后定时任务同步数据库里面去
        方案二：直接插入数据库，然后查询的时候放到缓存里面，从缓存查询
        注：缓存要有清除策略！！！
     */
    @Override
    public Boolean sendNormalMsg(NormalMsgDto normalMsgDto) {
        return null;
    }
}

