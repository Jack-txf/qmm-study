package com.feng.chat.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.chat.entity.dto.FriendDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.feng.chat.entity.ChatUser;

/**
 * (ChatUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@Mapper
public interface ChatUserMapper extends BaseMapper<ChatUser> {
    /**
    * 批量新增数据（MyBatis原生foreach方法）
    *
    * @param entities List<ChatUser> 实例对象列表
    * @return 影响行数
    */
    int insertBatch(@Param("entities") List<ChatUser> entities);

    List<FriendDto> selectFriends(@Param("uid") Long uid);

    void updateNick(@Param("nick") String nick, @Param("uid") Long uid);

    void updatePwd(@Param("uid") Long uid, @Param("password") String password);

    int judgeAlreadyFriend(@Param("uid") Long uid, @Param("uid1") Long uid1);
}

