package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.service.ChatUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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

}

