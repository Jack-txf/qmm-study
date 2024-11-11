package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.dto.FriendDto;
import com.feng.chat.entity.dto.LoginUser;
import com.feng.chat.entity.dto.UpdateFormDto;
import com.feng.chat.exception.MyException;
import com.feng.chat.service.ChatUserService;
import com.feng.chat.utils.UserContextUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (ChatUser)表控制层
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@RestController
@RequestMapping("/chatUser")
@CrossOrigin
public class ChatUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ChatUserService chatUserService;

    @PostMapping("/logout")
    public R chatUserLogout() {
        return chatUserService.logout();
    }

    @PostMapping("/login1")
    public R chatUserLogin(@RequestBody @Validated LoginUser loginUser) {
        return chatUserService.loginByUsernamePassword(loginUser);
    }

    // 获取用户的好友
    @GetMapping("/getMyFriends")
    public R getMyFriends() {
        List<FriendDto> friendDtos = chatUserService.getMyFriends(UserContextUtil.getUid());
        return R.success().setData("friends", friendDtos);
    }

    // 获取个人基本信息
    @GetMapping("/getMyInfo")
    public R getMyBaseInfo() {
        return chatUserService.getMyBaseInfo();
    }
    // 修改昵称
    @PostMapping("/updateNick")
    public R updateNick( @RequestBody @Validated UpdateFormDto updateFormDto ) {
        return chatUserService.updateNick(updateFormDto);
    }

    // 修改密码
    @PostMapping("/updatePwd")
    public R updatePwd(@RequestBody @Validated UpdateFormDto updateFormDto ) {
        return chatUserService.updatePwd(updateFormDto);
    }

    @GetMapping("/test")
    public R test() {
        System.out.println("hahah");
        return R.success();
    }

}

