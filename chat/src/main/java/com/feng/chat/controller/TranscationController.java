package com.feng.chat.controller;

import com.feng.chat.common.R;
import com.feng.chat.service.TransService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
@RestController
@RequestMapping("/trans")
public class TranscationController {
    @Resource
    private TransService transService;

    // 1.最简单的声明式事务
    @PostMapping("/simple")
    public R demo1() {
        transService.simple();
        return R.success().setData("msg", "最简单的");
    }

    //2.事务简单嵌套
    @PostMapping("/simplet")
    public R demo2() {
        transService.simplet();
        return R.success().setData("msg", "最简单的");
    }

    //3. 两个独立的
    @PostMapping("/simpled")
    public R demo3() {
        // transService.simpled(); 这个里面事务会失效

        transService.insert();
        transService.simple();

        return R.success().setData("msg", "最简单的");
    }
}
