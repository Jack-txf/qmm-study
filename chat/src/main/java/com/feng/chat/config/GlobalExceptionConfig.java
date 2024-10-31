package com.feng.chat.config;

import com.feng.chat.common.R;
import com.feng.chat.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionConfig {
    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public R bizExceptionHandler(HttpServletRequest req, MyException e){
        log.error("请求: " + req.getRequestURI());
        log.error("发生业务异常！原因是：{}",e.getMessage());
        return R.fail(4444).setData("msg", e.getMessage());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是: {}", e.toString());
        return R.fail(500).setData("msg","空指针异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R exception(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        log.error("请求[ {} ] {} 的参数校验发生错误", request.getMethod(), request.getRequestURL());
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            log.error("参数 {} = {} 校验错误：{}", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
        }
        return R.fail(402).setData("msg","参数异常");
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, Exception e){
        // 这个是那个异步任务判断的.. 见async-study
        if (e instanceof CompletionException) { // 为什么要再判断一次？ 兜底判断，如果MyException被其他异常包起来了.
            Throwable cause = e.getCause();
            if (cause instanceof MyException) {
                MyException myException = (MyException) cause;
                log.error("发生业务异常！原因是：{}", myException.getErrorMsg());
                return R.fail(4444);
            }
        }

        log.error("未知异常，{}", e.getMessage());
        return R.fail(4444).setData("msg", "未知异常！");
    }
}
