package com.feng.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.chat.entity.SysMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Mapper
public interface SysmsgMapper extends BaseMapper<SysMsg> {
    List<SysMsg> selectNeedReadMsg(@Param("toUser") Long toUser);
}
