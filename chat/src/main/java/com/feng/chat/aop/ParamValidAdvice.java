package com.feng.chat.aop;

import com.feng.chat.annotation.ParamSign;
import com.feng.chat.common.R;
import com.feng.chat.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/13
 */
@Component
@Aspect
public class ParamValidAdvice {
    // 定义切点
    @Pointcut("@annotation(com.feng.chat.annotation.ParamsValid)")
    public void pointcut() {

    }

    //ProceedingJoinPoint 只有在环绕通知才会有这个参数
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // // 获取参数的值！！
        // Object[] args = joinPoint.getArgs();
        // // 获取方法
        // Method method = signature.getMethod();
        // Parameter[] parameters = method.getParameters(); // 方法的参数列表
        //
        // for (int i = 0; i < parameters.length; i++) {
        //     // 判断参数上时候添加了ParamsSign注解，然后获取到注解的信息
        //     ParamSign paramSign = parameters[i].getAnnotation(ParamSign.class);
        //     String[] suffix = paramSign.suffix(); // 注解标注的字符串后缀
        //     int length = paramSign.length(); // 注解标注的长度
        //
        //     // 判断实际的参数
        //     if ( args[i] instanceof String ) { // 看这个参数是不是String类型
        //         String tmp = (String) args[i];
        //         int i1 = tmp.indexOf(".");
        //         if ( i1 == -1 ) return R.fail().setData("msg", "没有.后缀的字符串！");
        //         String realSuffix = tmp.substring(i1);
        //         if ( tmp.length() > length ) {
        //             return R.fail().setData("msg", String.format("字符串长度：%sByte，超过限定大小：%sByte", tmp.length(), length));
        //         }
        //         if ( !contains(suffix, realSuffix) ) {
        //             return R.fail().setData("msg", String.format("不支持这个后缀：%s", realSuffix));
        //         }
        //     }
        // }
        //
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private boolean contains(String[] suffix, String realSuffix) {
        boolean sign = false;
        for (String s : suffix) {
            if ( realSuffix.equals(s) ) sign = true;
        }
        return sign;
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取参数的值！！
        Object[] args = joinPoint.getArgs();
        // 获取方法
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters(); // 方法的参数列表

        for (int i = 0; i < parameters.length; i++) {
            // 判断参数上时候添加了ParamsSign注解，然后获取到注解的信息
            ParamSign paramSign = parameters[i].getAnnotation(ParamSign.class);
            String[] suffix = paramSign.suffix(); // 注解标注的字符串后缀
            int length = paramSign.length(); // 注解标注的长度

            // 判断实际的参数
            if ( args[i] instanceof String ) { // 看这个参数是不是String类型
                String tmp = (String) args[i];
                int i1 = tmp.indexOf(".");
                if ( i1 == -1 ) throw new MyException("没有.后缀的字符串！");
                String realSuffix = tmp.substring(i1);
                if ( tmp.length() > length ) {
                    throw new MyException(String.format("字符串长度：%sByte，超过限定大小：%sByte", tmp.length(), length));
                }
                if ( !contains(suffix, realSuffix) ) {
                    throw new MyException(String.format("不支持这个后缀：%s", realSuffix));
                }
            }
        }
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {

    }

    @After("pointcut()")
    public void after() {

    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {

    }
}

