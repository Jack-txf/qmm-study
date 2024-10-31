package com.feng.chat.netty;

import com.feng.chat.netty.server.Netty1;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NettyServer1 {
    private Netty1 netty1;
    @PostConstruct
    public void initServer() {
        // netty1 = new Netty1(10000);
        // netty1.start();
    }

    @PreDestroy
    public void stopServer() {
        // if (netty1 != null) {
        //     // 这里需要实现NettyServer的优雅关闭逻辑
        //     // 通常需要关闭EventLoopGroup并等待当前处理的任务完成
        //     netty1.stop(); // 假设NettyServer有一个stop方法来关闭服务
        // }
    }
}
