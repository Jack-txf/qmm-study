package com.feng.mq.rocketmq.consumer1;

import com.feng.mq.config.ConsumerGroup;
import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.logging.org.slf4j.MDC;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description: 消费者可靠性
 * @Author: txf
 * @Date: 2025/6/29
 */
@Service
@Slf4j
@RocketMQMessageListener(
    topic = TopicConstant.CONSUMER_RELIABILITY_TOPIC,
    consumerGroup = ConsumerGroup.CONSUMER_RELIABILITY,
    messageModel = MessageModel.CLUSTERING,
    maxReconsumeTimes = 2, // 最大重试次数
    suspendCurrentQueueTimeMillis = 1500 // 暂停时间
)
public class ReliabilityConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("【处理订单消息-准备扣减库存========================】");
        boolean res = handleMessage(s);
    }

    private boolean handleMessage(String message) {
        // 随机生成true或者false
        Random random = new Random();
        // boolean flag = random.nextBoolean();

        boolean flag = false;
        log.info("处理消息：{}", message);
        if ( !flag ) {
            log.error("处理消息失败--出现了意想不到的异常");
            throw new RuntimeException("处理消息失败");
        }
        // 处理消息
        return true;
    }
}
