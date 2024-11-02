package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.dto.LoginUser;
import com.feng.chat.exception.MyException;
import com.feng.chat.service.ChatUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * (ChatUser)表控制层
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@RestController
@RequestMapping("/chatUser")
public class ChatUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ChatUserService chatUserService;

    @PostMapping("/login1")
    public R chatUserLogin(@RequestBody @Validated LoginUser loginUser) {
        String token = chatUserService.loginByUsernamePassword(loginUser);
        return R.success().setData("msg", "登录成功！")
                .setData("chatToken", token);
    }

    @GetMapping("/test")
    public R test() {
        System.out.println("hahah");
        return R.success();
    }

}

