package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.chatenum.SysmsgType;
import com.feng.chat.entity.FriendRelation;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.dto.UnReadSysMsgDTO;
import com.feng.chat.entity.vo.UnReadSysMsgVo;
import com.feng.chat.mapper.FriendRelationMapper;
import com.feng.chat.mapper.SysmsgMapper;
import com.feng.chat.service.SysmsgService;
import com.feng.chat.utils.ConvertUtil;
import com.feng.chat.utils.UserContextUtil;
import com.feng.chat.websocket.WebSocketChatServerHandler;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Service("sysmsgService")
public class SysmsgServiceImpl extends ServiceImpl<SysmsgMapper, SysMsg> implements SysmsgService {
    @Resource
    private SysmsgMapper sysmsgMapper;
    @Resource
    private WebSocketChatServerHandler webSocketChatServerHandler;
    @Resource
    private FriendRelationMapper friendRelationMapper;

    @Override
    public int judgeHasSendInvite(Long uid, Long uid1) {
        return sysmsgMapper.judgeHasSendInvite(uid, uid1);
    }

    @Override
    public List<UnReadSysMsgDTO> getMySysMsgs(int page, int size, int type ) {
        Long me = UserContextUtil.getUid();
        int offsetVal = (page-1) * size;
        List<UnReadSysMsgVo> unReads = sysmsgMapper.selectNeedReadMsgByPage(me, size, offsetVal, type);
        if ( unReads == null || unReads.isEmpty()) return null;
        //转化为dto
        return ConvertUtil.convertSysmsgToDTO(unReads);
    }

    // 用户处理消息
    /*
        1. 数据库里面修改一下
        2. 消息类型
            - 消息徽章数减1
            - 好友申请同意（给双方发送一个消息--刷新好友列表）
            - 群聊加入申请统一 ( 待做 --)
     */
    @Override
    public boolean tackleMsg(Long sysmsgId, Integer isAccept) {
        // 1.先查出这条消息
        SysMsg sysMsg = sysmsgMapper.selectById(sysmsgId);
        // 2.更新为isAccept状态
        boolean update = this.lambdaUpdate().eq(SysMsg::getSysmsgId, sysmsgId).set(SysMsg::getIsRead, 1)
                .set(SysMsg::getIsAccept, isAccept).update();
        // 如果是好友申请的系统消息
        if ( sysMsg.getMsgType().equals(SysmsgType.FriendInvite.getSysmsgType())) {
            // 如果是接受, 同意好友申请（插入好友关系表），拒绝就不用插入数据库表了
            // 给他们俩发消息--刷新好友列表
            if ( isAccept.equals(1)) {
                friendRelationMapper.insert(new FriendRelation(null, sysMsg.getSendUser(), sysMsg.getToUser(), null, null));
                List<Long> ids = Arrays.asList(sysMsg.getSendUser(), sysMsg.getToUser());
                webSocketChatServerHandler.sendSysMsgFlushFriends(ids);
            }
            // 给当前用户发送一个实时的消息未读徽章数
            webSocketChatServerHandler.sendMsgBadge(UserContextUtil.getUid());
        }
        return update;
    }
}
