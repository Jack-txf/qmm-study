package com.feng.chat.annotation;

import java.lang.annotation.*;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
// 用在方法上
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ParamsValid {
}
