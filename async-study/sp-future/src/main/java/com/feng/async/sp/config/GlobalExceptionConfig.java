package com.feng.async.sp.config;

import com.feng.async.sp.common.ServletResponse;
import com.feng.async.sp.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletionException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionConfig {
    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ServletResponse bizExceptionHandler(HttpServletRequest req, MyException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ServletResponse.builder().code(e.getErrorCode()).msg("出现了业务异常: " + e.getErrorMsg()).build();
    }

    /**
     * 处理空指针的异常

     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ServletResponse exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是: {}", e.toString());
        return ServletResponse.builder().code(4444).msg("空指针异常").build();
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServletResponse exceptionHandler(HttpServletRequest req, Exception e){
        if (e instanceof CompletionException) { // 为什么要再判断一次？ 兜底判断，如果MyException被其他异常包起来了.
            Throwable cause = e.getCause();
            if (cause instanceof MyException) {
                MyException myException = (MyException) cause;
                log.error("发生业务异常！原因是：{}", myException.getErrorMsg());
                return ServletResponse.builder().code(myException.getErrorCode()).msg("出现了业务异常: " + myException.getErrorMsg()).build();
            }
        }

        return ServletResponse.builder().code(4444).msg("未知异常").build();
    }
}
