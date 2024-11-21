package com.feng.chat.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.chat.entity.dto.HistoryMsgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.feng.chat.entity.ChatMsg;

/**
 * (ChatMsg)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-31 20:35:28
 */
@Mapper
public interface ChatMsgMapper extends BaseMapper<ChatMsg> {
    /**
    * 批量新增数据（MyBatis原生foreach方法）
    *
    * @param entities List<ChatMsg> 实例对象列表
    * @return 影响行数
    */
    int insertBatch(@Param("entities") List<ChatMsg> entities);

    List<ChatMsg> selectHistorySegment(@Param("me") Long me, @Param("uid") Long uid);

    List<HistoryMsgDto> getHistoryPage(@Param("uid") Long uid, @Param("me") Long me ,@Param("pageSize") Integer pageSize, @Param("offset") int offset);
}

