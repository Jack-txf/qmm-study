package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.common.R;
import com.feng.chat.entity.dto.HistoryMsgDto;
import com.feng.chat.entity.dto.HistoryMsgSegmentDto;
import com.feng.chat.entity.dto.NormalMsgDto;
import com.feng.chat.mapper.ChatMsgMapper;
import com.feng.chat.entity.ChatMsg;
import com.feng.chat.service.ChatMsgService;
import com.feng.chat.utils.ConvertUtil;
import com.feng.chat.utils.UserContextUtil;
import com.feng.chat.websocket.WebSocketChatServerHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * (ChatMsg)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 20:35:30
 */
@Service("chatMsgService")
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {
    /* 1. 发送普通消息( 单人对单人 )
        方案一：先存入redis，然后定时任务同步数据库里面去 ( xxl-job )
        方案二：直接插入数据库，然后查询的时候放到缓存里面，从缓存查询
        注：缓存要有清除策略！！！

        这里图简便，就直接在数据库里面操作了
     */
    @Resource
    private ChatMsgMapper chatMsgMapper;
    // @Resource
    // private WebSocketChatServerHandler webSocketChatServerHandler;

    // @Override
    // public Boolean sendNormalMsg(NormalMsgDto normalMsgDto) {
    //     // 直接插入数据库
    //     ChatMsg chatMsg = ConvertUtil.convertNormalMsgToChatMsg(normalMsgDto);
    //     int insert = chatMsgMapper.insert(chatMsg);
    //     // if ( insert > 0 ) { // 给toUser发送一条消息
    //     //     webSocketChatServerHandler.sendChatMsgToOne(chatMsg);
    //     // }
    //     return insert > 0;
    // }

    @Override
    public List<HistoryMsgSegmentDto> getHistorySegment(Long uid) {
        Long me = UserContextUtil.getUid();
        List<ChatMsg> msgs = chatMsgMapper.selectHistorySegment(me, uid); // 要把这个逆序一下
        if ( msgs != null && !msgs.isEmpty()) {
            Collections.reverse(msgs); //
            return ConvertUtil.convertSegToDto(msgs);
        }
        return Collections.emptyList();
    }

    @Override
    public List<HistoryMsgDto> getHistoryPage(Long uid, Integer page, Integer pageSize) {
        int offset = (page - 1) * pageSize;
        Long me = UserContextUtil.getUid();
        List<HistoryMsgDto> page1 = chatMsgMapper.getHistoryPage(me, uid, pageSize, offset);
        if ( page1 != null && !page1.isEmpty()) {
            Collections.reverse(page1); //
            return page1;
        }
        return Collections.emptyList();
    }
}

