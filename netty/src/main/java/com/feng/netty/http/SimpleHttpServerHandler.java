package com.feng.netty.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SimpleHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        try {
            System.out.println("请求来了:" + request.uri());

            String result = doHandle(request);
            System.out.println("处理结果： " + result);
            byte[] responseBytes = result.getBytes(StandardCharsets.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(responseBytes));
            response.headers().set("Content-Type", "text/html; charset=utf-8");
            response.headers().setInt("Content-Length", response.content().readableBytes());

            boolean isKeepAlive = HttpUtil.isKeepAlive(response);
            if (!isKeepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set("Connection", "keep-alive");
                ctx.write(response);
            }
        } catch (Exception e) {
            System.out.println("出错了：" + e );
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("SimpleHttpServerHandler exception,");
        System.out.println(cause);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) { // 读操作那个方法完成之后，channelRead0完成之后
        ctx.flush();
    }

    private String doHandle(FullHttpRequest fullHttpRequest) {
        HttpMethod method = fullHttpRequest.method();
        if (HttpMethod.GET == method) {
            System.out.println("是get请求");
            System.out.println(fullHttpRequest.uri());
            String res = "<h1> get请求，你好呀，netty！</h1>";
            return JSON.toJSONString(res);
        } else if (HttpMethod.POST == fullHttpRequest.method()) {
            System.out.println("是post请求");
            System.out.println(fullHttpRequest.uri());

            ByteBuf content = fullHttpRequest.content();
            String string = content.toString(StandardCharsets.UTF_8);
            System.out.println(string); // 请求体
            User user = JSONObject.parseObject(string, User.class);
            System.out.println("传来一个user: " + user);

            HashMap<String, Object> resMap = new HashMap<>();
            String res = "<h1> post请求，你好呀，netty！</h1>";
            resMap.put("msg", res);
            resMap.put("user", user);

            return JSON.toJSONString(resMap);
        }

        return "";
    }
}
