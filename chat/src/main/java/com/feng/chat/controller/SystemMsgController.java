package com.feng.chat.controller;

import com.feng.chat.common.R;
import com.feng.chat.entity.dto.UnReadSysMsgDTO;
import com.feng.chat.service.SysmsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/14
 */
@RestController
@RequestMapping("/chatmsg")
public class SystemMsgController {
    @Resource
    private SysmsgService sysmsgService;

    @GetMapping("/getSysMsgs")
    public R getMySysMsg(@RequestParam("page")Integer page, @RequestParam("size") Integer size) {
        List<UnReadSysMsgDTO> msgs = sysmsgService.getMySysMsgs(page, size);
        return R.success().setData("sysMsgs", msgs);
    }
}
