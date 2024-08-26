package com.feng.chat.controller;

import com.feng.chat.annotation.ParamSign;
import com.feng.chat.annotation.ParamsValid;
import com.feng.chat.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
@RestController
@RequestMapping("/valid")
public class ParamValidController {

    /*
    自定义注解，来校验参数的有效性
     */
    @GetMapping("/getValid")
    @ParamsValid
    public R valid(@RequestParam("fileString") @ParamSign String fileString) {
        System.out.println(fileString);
        return R.success();
    }
}
