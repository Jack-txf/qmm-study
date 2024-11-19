package com.feng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.chat.entity.ChatMsg;
import com.feng.chat.entity.dto.NormalMsgDto;

/**
 * (ChatMsg)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 20:35:30
 */
public interface ChatMsgService extends IService<ChatMsg> {

    Boolean sendNormalMsg(NormalMsgDto normalMsgDto);
}

