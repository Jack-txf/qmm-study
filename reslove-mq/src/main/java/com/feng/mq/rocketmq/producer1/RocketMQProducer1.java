package com.feng.mq.rocketmq.producer1;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class RocketMQProducer1 {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    private final String topic = "demo-topic";
    // 1.同步发送消息
    public void sendSyncMessage(String message){
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build());
        System.out.printf("同步发送结果: %s\n", message);
    }
    // 2.异步发送消息
    public void sendAsyncMessage(String message){
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(message).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("异步发送成功: %s\n", sendResult);
            }
            @Override
            public void onException(Throwable throwable) {
                System.out.printf("异步发送失败: %s\n", throwable.getMessage());
            }
        });
    }
    // 3.单向发送消息
    public void sendOneWayMessage(String message){
        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(message).build());
        System.out.println("单向消息发送成功");
    }
}
