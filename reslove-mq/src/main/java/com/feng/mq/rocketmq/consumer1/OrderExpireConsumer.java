package com.feng.mq.rocketmq.consumer1;

import com.feng.mq.config.ConsumerGroup;
import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/6/27 14:20
 * @注释 订单过期消息消费者
 */
@Service
@Slf4j
@RocketMQMessageListener(
        topic = TopicConstant.ORDER_EXPIRE_TOPIC,
        consumerGroup = ConsumerGroup.ORDER_EXPIRE_GROUP,
        messageModel = MessageModel.CLUSTERING
        // consumeThreadMax = 1,
        // consumeThreadNumber = 1
)
public class OrderExpireConsumer implements RocketMQListener<String> {
    public void onMessage(String message) {
        log.info("【===============================】");
        log.info("【订单过期消息消费者】收到消息：{}", message);
        log.info("【订单过期消息消费者】处理订单消息开始......修改订单状态..等等操作");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("【===============================】");
        // 处理订单过期消息.....
    }
}
