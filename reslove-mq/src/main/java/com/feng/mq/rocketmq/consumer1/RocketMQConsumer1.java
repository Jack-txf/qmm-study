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
        messageModel = MessageModel.CLUSTERING
)
public class RocketMQConsumer1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.printf("收到普通消息: %s\n", s);
    }
}

