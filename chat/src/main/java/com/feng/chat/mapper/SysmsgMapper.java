package com.feng.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.vo.UnReadSysMsgVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Mapper
public interface SysmsgMapper extends BaseMapper<SysMsg> {
    List<UnReadSysMsgVo> selectNeedReadMsg(@Param("toUser") Long toUser);

    int judgeHasSendInvite(@Param("uid") Long uid, @Param("uid1") Long uid1);

    List<UnReadSysMsgVo> selectNeedReadMsgByPage(@Param("me")Long me, @Param("size")int size, @Param("offsetVal")int offsetVal);
}
