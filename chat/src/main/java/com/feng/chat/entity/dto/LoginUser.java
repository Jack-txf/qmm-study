package com.feng.chat.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginUser {
    @NotBlank(message = "用户账户不能为空")
    @Size(min = 10, max = 20, message = "账号长度在10-20位之间")
    private String username;

    @NotBlank(message = "账号密码不能为空")
    @Size(min = 9, max = 20, message = "账号密码长度在9-20位之间")
    private String password;
}
