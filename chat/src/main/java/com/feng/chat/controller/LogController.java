package com.feng.chat.controller;

import com.feng.chat.annotation.LogSign;
import com.feng.chat.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/getTest")
    @LogSign( describe = "日志记录测试")
    public R logRecord() {
        System.out.println("齐天大圣孙悟空！！");
        return R.success().setData("msg", "OK啦");
    }
}
