package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.dto.UnReadSysMsgDTO;
import com.feng.chat.entity.vo.UnReadSysMsgVo;
import com.feng.chat.mapper.SysmsgMapper;
import com.feng.chat.service.SysmsgService;
import com.feng.chat.utils.ConvertUtil;
import com.feng.chat.utils.UserContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Service("sysmsgService")
public class SysmsgServiceImpl extends ServiceImpl<SysmsgMapper, SysMsg> implements SysmsgService {
    @Resource
    private SysmsgMapper sysmsgMapper;
    @Override
    public int judgeHasSendInvite(Long uid, Long uid1) {
        return sysmsgMapper.judgeHasSendInvite(uid, uid1);
    }

    @Override
    public List<UnReadSysMsgDTO> getMySysMsgs(int page, int size ) {
        Long me = UserContextUtil.getUid();
        List<UnReadSysMsgVo> unReads = sysmsgMapper.selectNeedReadMsgByPage(me, page, size);
        if ( unReads == null || unReads.isEmpty()) return null;
        //转化为dto
        return ConvertUtil.convertSysmsgToDTO(unReads);
    }
}
