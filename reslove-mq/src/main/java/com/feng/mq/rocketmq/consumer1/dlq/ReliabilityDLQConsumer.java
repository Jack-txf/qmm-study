package com.feng.mq.rocketmq.consumer1.dlq;

import com.feng.mq.config.ConsumerGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
/**
 * @Description: 死信消费者
 * @Author: txf
 * @Date: 2025/6/29
 */
@Service
@Slf4j
@RocketMQMessageListener(
    topic = "%DLQ%" + ConsumerGroup.CONSUMER_RELIABILITY, // 死信TOPIC，注意他的组成
    consumerGroup = ConsumerGroup.CONSUMER_RELIABILITY_DLQ, // 死信队列消费组
    messageModel = MessageModel.CLUSTERING
)
public class ReliabilityDLQConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("【死信队列消费者】收到消息：{}", s);
        // 通知
        log.info("【死信队列消费者】处理死信消息开始......通知人工处理~~~~");
        // 存储该死信
        log.info("【死信队列消费者】处理死信消息结束......存储数据..");
    }
}
