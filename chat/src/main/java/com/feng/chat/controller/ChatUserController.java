package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.exception.MyException;
import com.feng.chat.service.ChatUserService;
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

    @PostMapping("/login")
    public R chatUserLogin(@RequestBody ChatUser chatUser) {
        if (Objects.isNull(chatUser.getUsername()) || Objects.isNull(chatUser.getPassword())) {
            throw new MyException(444, "用户参数异常！不能为空!");
        }
        Optional<ChatUser> chatUser1 = Optional.ofNullable(chatUser);

        return R.success();
    }

    @GetMapping("/test")
    public R test() {
        System.out.println("hahah");
        return R.success();
    }

}

