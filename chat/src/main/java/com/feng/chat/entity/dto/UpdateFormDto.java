package com.feng.chat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFormDto {
    @Size(max = 20, message = "昵称长度最长30位")
    private String nick;
    @Size(max = 20, message = "密码长度最长20位")
    private String oldPassword;
    @Size(max = 20, message = "密码长度最长20位")
    private String password;
}
