package com.feng.mq.rocketmq.consumer1;

import com.feng.mq.config.TopicConstant;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = TopicConstant.SP_Normal_Topic,
        consumerGroup = "consumer-group01",
        /*
        集群消费模式：当使用集群消费模式时，RocketMQ 认为任意一条消息只需要被消费组内的任意一个消费者处理即可。
        广播消费模式：当使用广播消费模式时，RocketMQ 会将每条消息推送给消费组所有的消费者，保证消息至少被每个消费者消费一次。
        */
        messageModel = MessageModel.CLUSTERING
)
public class RocketMQConsumer1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.printf("收到普通消息: %s\n", s);
    }
}

