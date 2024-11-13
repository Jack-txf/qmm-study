package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.mapper.SysmsgMapper;
import com.feng.chat.service.SysmsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
