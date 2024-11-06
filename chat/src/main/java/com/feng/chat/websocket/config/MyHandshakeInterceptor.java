package com.feng.chat.websocket.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // ws://127.0.0.1/txfChat?token=123q4weqew8qeqew3q1wq5e4qw-qwe
        // 获取查询字符串
        String queryString = request.getURI().getQuery();
        if (queryString != null) {
            String[] split = queryString.split("=");
            if ( split.length >= 2 ) {
                String tokenKey = split[0]; // token
                String token = split[1];
                // 存储参数到attributes中，供后续使用
                attributes.put(tokenKey, token);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}