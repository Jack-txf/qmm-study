package com.feng.chat.aop;

import com.feng.chat.annotation.LogSign;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/26
 */
@Component
@Aspect
@Slf4j
public class LogAdvice {
    @Pointcut("@annotation(com.feng.chat.annotation.LogSign)")
    public void pointCut() {}

    /*
    环绕---( aroud中的point.proceed() 之前的代码)
    前---- before
    返回结果之后------AfterReturning
    执行后------after
    环绕之后----( aroud中的point.proceed() 后面的代码 )
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object res = null;
        MethodSignature signature = (MethodSignature) point.getSignature(); // 得到方法
        LogSign logSign = signature.getMethod().getAnnotation(LogSign.class);
        String describe = logSign.describe();
        log.info("{} 开始执行 start~~~~", describe);
        try {
            res = point.proceed();
            log.info("{} 执行完成了 end~~~~", describe);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    // @Before("pointCut()")
    // public void before(JoinPoint point) {
    //     System.out.println("前----");
    // }
    //
    // @AfterReturning("pointCut()")
    // public void afterReturning(JoinPoint point) {
    //     System.out.println("返回结果之后------");
    // }
    //
    // @After("pointCut()")
    // public void after(JoinPoint point) {
    //     System.out.println("执行后------");
    // }

}
