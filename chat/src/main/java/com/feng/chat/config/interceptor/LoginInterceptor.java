package com.feng.chat.config.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.feng.chat.exception.MyException;
import com.feng.chat.utils.TokenSecretUtil;
import com.feng.chat.utils.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@SuppressWarnings("all")
public class LoginInterceptor implements HandlerInterceptor {

    // @Resource
    // private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MyException {
        log.info("【我是登录拦截器】" + request.getRequestURI());
        String token = request.getHeader("Chattoken");
        System.out.println("【token】:" + token);
        if ( token == null ) throw new MyException("您还未登录!");
        Long loginUserId = null;
        try {
            // Long uid = TokenSecretUtil.deCodeToken(token);
            // log.info("【请求的用户id】： {}", uid);
            loginUserId = Long.valueOf((String) StpUtil.getLoginId()); // 这个可能会抛异常
            UserContextUtil.set(loginUserId); // 设置进去用户id上下文
        } catch ( Exception e ) {
            // 抛了异常此次会话到期了，说明有问题
            throw new MyException("登录失效，请重新登录");
        }
        return true;
    }

    // 调用前提：preHandle返回true
    // 调用时间：DispatcherServlet进行视图的渲染之后
    // 多用于清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextUtil.remove(); // 资源清理
    }
}
