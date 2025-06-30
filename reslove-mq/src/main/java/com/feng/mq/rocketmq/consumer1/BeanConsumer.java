package com.feng.mq.rocketmq.consumer1;

import com.feng.mq.config.ConsumerGroup;
import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/6/30 14:48
 * @注释 @Bean的形式创建消费者
 */
@Component
@Slf4j
public class BeanConsumer {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Bean
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ConsumerGroup.CONSUMER_RELIABILITY);
        consumer.setNamesrvAddr(nameServer);
        consumer.subscribe(TopicConstant.CONSUMER_RELIABILITY_TOPIC, "*");

        // 配置重试参数
        consumer.setMaxReconsumeTimes(3);  // 最多重试3次
        consumer.setSuspendCurrentQueueTimeMillis(10000);  // 重试间隔10秒

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            MessageExt messageExt = msgs.get(0);
            String msg = new String(messageExt.getBody());
            log.info("【===============================】 {}", msg);
            try {
                // 业务逻辑....
                Random random = new Random();
                boolean b = random.nextBoolean();
                if (b) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                throw new Exception("消费失败");
            } catch (Exception e) {
                log.error("消费失败--{}", e.getMessage());
                // 记录重试次数
                int reconsumeTimes = msgs.get(0).getReconsumeTimes();
                log.error("消费失败，重试次数：{}", reconsumeTimes, e);
                // 达到最大重试次数后转死信队列
                if (reconsumeTimes >= 2) {  // 已重试2次，本次是第3次
                    // 发送到死信队列：sendToDLQ(msgs.get(0));
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 触发重试
            }
        });

        consumer.start();
        return consumer;

    }
}
