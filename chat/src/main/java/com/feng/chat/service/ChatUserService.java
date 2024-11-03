package com.feng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.dto.FriendDto;
import com.feng.chat.entity.dto.LoginUser;

import java.util.List;

/**
 * (ChatUser)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
public interface ChatUserService extends IService<ChatUser> {

    String loginByUsernamePassword(LoginUser loginUser);

    List<FriendDto> getMyFriends(Long uid);
}

