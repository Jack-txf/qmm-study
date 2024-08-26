package com.feng.chat.annotation;

import java.lang.annotation.*;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
// 用在参数上
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface ParamSign {
    // 字符串后缀
    String[] suffix() default {"txt", "json", "png", "jpge", "jpg"};

    // 字符串长度
    int length() default 50;
}
