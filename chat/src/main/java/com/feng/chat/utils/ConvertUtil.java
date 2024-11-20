package com.feng.chat.utils;

import com.feng.chat.entity.ChatMsg;
import com.feng.chat.entity.dto.NormalMsgDto;
import com.feng.chat.entity.dto.UnReadSysMsgDTO;
import com.feng.chat.entity.vo.UnReadSysMsgVo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/14
 */
public class ConvertUtil {
    public static List<UnReadSysMsgDTO> convertSysmsgToDTO(List<UnReadSysMsgVo> unReads) {
        ArrayList<UnReadSysMsgDTO> dtos = new ArrayList<>();
        UnReadSysMsgVo sysMsg;
        for (int i = 0; i < unReads.size(); i++) {
            sysMsg = unReads.get(i);
            UnReadSysMsgDTO dto = new UnReadSysMsgDTO();
            dto.setSysmsgId(sysMsg.getSysmsgId()); // 消息id
            dto.setNick(sysMsg.getNick()); // 谁发来的
            dto.setPhone(sysMsg.getPhone());
            dto.setCreateTime(sysMsg.getCreateTime());
            dto.setMsgType(sysMsg.getMsgType());
            // 0-好友申请  1-加入群聊申请  2-邀请加入群聊
            if ( sysMsg.getMsgType() == 0 ) {
                dto.setMsgContent( "【" + dto.getNick() + "】申请添加您为好友！");
            } else if ( sysMsg.getMsgType() == 1 ) {
                dto.setMsgContent( "【"  + dto.getNick() + "】申请加入群聊！");
            } else {
                dto.setMsgContent( "【"  + dto.getNick() + "】邀请您加入群聊！");
            }
            dto.setIsAccept(sysMsg.getIsAccept());
            dto.setUsername(sysMsg.getUsername());
            dto.setSendUser(dto.getSendUser()); // 发送者id
            dto.setToUser(UserContextUtil.getUid());

            dtos.add(dto);
        }
        return dtos;
    }

    public static ChatMsg convertNormalMsgToChatMsg(NormalMsgDto normalMsgDto) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setFromUser(normalMsgDto.getFromUser());
        chatMsg.setToUser(normalMsgDto.getToUser());
        chatMsg.setMsgDesc(normalMsgDto.getMsgDesc());
        chatMsg.setIsRead(false);
        return chatMsg;
    }
}
