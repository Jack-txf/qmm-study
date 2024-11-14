package com.feng.chat.utils;

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
        UnReadSysMsgVo sysMsg = null;
        for (int i = 0; i < unReads.size(); i++) {
            sysMsg = unReads.get(i);
            UnReadSysMsgDTO dto = new UnReadSysMsgDTO();
            dto.setSysmsgId(sysMsg.getSysmsgId()); // 消息id
            dto.setNick(sysMsg.getNick()); // 谁发来的
            dto.setPhone(sysMsg.getPhone());
            dto.setCreateTime(sysMsg.getCreateTime());
            dto.setMsgType(sysMsg.getMsgType());
            if ( sysMsg.getMsgType() == 0 ) {
                dto.setMsgContent( dto.getNick());
            } else if ( sysMsg.getMsgType() == 1 ) {

            } else {

            }

            dto.setIsAccept(0);
            dto.setUsername(sysMsg.getUsername());
            dto.setSendUser(dto.getSendUser()); // 发送者id
            dto.setToUser(UserContextUtil.getUid());



            dtos.add(dto);
        }
        return dtos;
    }
}
