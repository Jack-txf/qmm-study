package com.feng.mq.idempotency;

import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/7/1 11:14
 * @注释 幂等性消息生产者
 */
@Service
@Slf4j
public class IdempotentProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    // 发送一个普通消息--topic是 idempotent-topic
    public void sendNormalMessage(String message){
        Message<String> msg = MessageBuilder.withPayload(message)
                // 发送消息的时候附带一个唯一标识 -- 这里仅是举个例子，实际使用中请自行处理
                // 需确保全局唯一性，推荐使用业务流水号（如订单号+时间戳）或UUID。
                .setHeader("msgId", UUID.randomUUID().toString().substring(0, 8))
                .build();
        rocketMQTemplate.asyncSend(TopicConstant.IDEMPOTENT_TOPIC, msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功: {}", sendResult);
            }
            @Override
            public void onException(Throwable e) {
                log.error("发送失败: {}", e.getMessage());
            }
        });
    }
}
