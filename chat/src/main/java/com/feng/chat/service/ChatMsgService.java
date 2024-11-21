package com.feng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.chat.entity.ChatMsg;
import com.feng.chat.entity.dto.HistoryMsgDto;
import com.feng.chat.entity.dto.HistoryMsgSegmentDto;
import com.feng.chat.entity.dto.NormalMsgDto;

import java.util.List;

/**
 * (ChatMsg)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 20:35:30
 */
public interface ChatMsgService extends IService<ChatMsg> {
    List<HistoryMsgSegmentDto> getHistorySegment(Long uid);

    List<HistoryMsgDto> getHistoryPage(Long uid, Integer page, Integer pageSize);

    // Boolean sendNormalMsg(NormalMsgDto normalMsgDto);
}

