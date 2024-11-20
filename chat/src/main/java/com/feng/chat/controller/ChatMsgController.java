package com.feng.chat.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.feng.chat.common.R;
import com.feng.chat.entity.dto.HistoryMsgSegmentDto;
import com.feng.chat.service.ChatMsgService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * (ChatMsg)表控制层
 *
 * @author makejava
 * @since 2024-07-31 20:35:28
 */
@RestController
@RequestMapping("/chatMsg")
public class ChatMsgController extends ApiController {
    /*
    聊天业务实现：
        1. 发送消息( 发送普通消息(单人对单人)，发送群聊消息 )
        2. 切换聊天对象时，查询一些历史消息
        3. 查询与该对象的所有历史消息（分页查询）
     */
    @Resource
    private ChatMsgService chatMsgService;

    /*
    1. 发送普通消息
     */
    // @PostMapping("/sendNormalMsg")
    // public R sendNormalMsg(@RequestBody @Validated NormalMsgDto normalMsgDto ) {
    //     Boolean res = chatMsgService.sendNormalMsg(normalMsgDto);
    //     return res ? R.success() : R.fail().setData("msg", "服务器开了点儿小差!");
    // }
    /*
    2. 发送群聊消息
     */
    // @PostMapping("/sendGroupMsg")
    // public R sendGroupMsg(@RequestBody NormalMsgDto normalMsgDto ) {
    //     return R.success();
    // }

    /*
    3. 查询部分历史消息(用于切换聊天对象的时候用的)
     */
    @GetMapping("/getHistorySegment/{uid}")
    public R getHistorySegment(@PathVariable("uid") Long uid) {
        List<HistoryMsgSegmentDto> historyList = chatMsgService.getHistorySegment(uid);
        return R.success().setData("messages", historyList);
    }

    /*
    4. 查询与该对象的所有历史消息（分页查询）
     */
    @GetMapping("/getHistoryPage")
    public R getHistoryPage() {
        return R.success();
    }

}

