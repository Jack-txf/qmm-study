package com.feng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.dto.UnReadSysMsgDTO;

import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
public interface SysmsgService extends IService<SysMsg> {
    int judgeHasSendInvite(Long uid, Long uid1);
    List<UnReadSysMsgDTO> getMySysMsgs(int page, int size, int type);

    boolean tackleMsg(Long sysmsgId, Integer isAccept);
}
