package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.service.ChatMsgService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (ChatMsg)表控制层
 *
 * @author makejava
 * @since 2024-07-31 20:35:28
 */
@RestController
@RequestMapping("chatMsg")
public class ChatMsgController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ChatMsgService chatMsgService;

}

