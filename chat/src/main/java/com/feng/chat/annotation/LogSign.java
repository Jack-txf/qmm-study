package com.feng.chat.annotation;

import java.lang.annotation.*;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LogSign {
    String describe(); // 日志说明
}
