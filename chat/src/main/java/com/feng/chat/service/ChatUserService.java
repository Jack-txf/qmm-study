package com.feng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.dto.FriendDto;
import com.feng.chat.entity.dto.LoginUser;
import com.feng.chat.entity.dto.UpdateFormDto;

import java.util.List;

/**
 * (ChatUser)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
public interface ChatUserService extends IService<ChatUser> {

    R loginByUsernamePassword(LoginUser loginUser);

    List<FriendDto> getMyFriends(Long uid);

    R logout();

    R getMyBaseInfo();

    R updateNick(UpdateFormDto updateFormDto);

    R updatePwd(UpdateFormDto updateFormDto);
}

