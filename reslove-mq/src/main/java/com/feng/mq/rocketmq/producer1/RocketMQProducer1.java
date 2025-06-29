package com.feng.mq.rocketmq.producer1;

import com.feng.mq.config.TopicConstant;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class RocketMQProducer1 {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    private final String topic = "demo-topic";
    // 1.同步发送普通消息
    public void sendSyncMessage(String message){
        SendResult sendResult = rocketMQTemplate.syncSend(TopicConstant.SP_Normal_Topic, MessageBuilder.withPayload(message).build());
        if ( sendResult.getSendStatus() == SendStatus.SEND_OK )
            System.out.printf("同步发送ok结果: %s\n", message);
    }
    // 2.异步发送消息
    public void sendAsyncMessage(String message){
        rocketMQTemplate.asyncSend(TopicConstant.SP_Normal_Topic, MessageBuilder.withPayload(message).build(), new SendCallback() {
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
        rocketMQTemplate.sendOneWay(TopicConstant.SP_Normal_Topic, MessageBuilder.withPayload(message).build());
        System.out.println("单向消息发送成功");
    }

    // 4.消费者可靠性测试
    public void sendReliabilityOrderTest( String orderId ) {
        SendResult sendResult = rocketMQTemplate.syncSend(TopicConstant.CONSUMER_RELIABILITY_TOPIC, MessageBuilder.withPayload(orderId).build());
        if ( sendResult.getSendStatus() == SendStatus.SEND_OK )
            System.out.printf("【消费者可靠性测试】同步发送ok结果: %s\n", orderId);
        else
            System.out.printf("【消费者可靠性测试】同步发送失败: %s\n", orderId);
    }
}
