package com.feng.mq.rocketmq.producer1;

import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/6/27 14:20
 * @注释 订单过期延迟消息生产者
 */
@Service
@Slf4j
public class OrderExpireProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendOrderExpireMessage(String message, long delayTime) {
        log.info("订单延迟消息发送，订单ID：{}，延迟时间：{}", message, delayTime);
        message = "[订单号]" + message + "|" + delayTime;
        SendResult sendResult = null;
        int retryCount = 0;
        do {
            try {
                sendResult = rocketMQTemplate.syncSendDelayTimeSeconds(TopicConstant.ORDER_EXPIRE_TOPIC, message, delayTime);
                //sendResult = rocketMQTemplate.syncSendDelayTimeSeconds(TopicConstant.ORDER_EXPIRE_TOPIC, message, 0); // 模拟错误
            } catch (Exception e) {
                log.error("订单延迟消息发送出现异常，订单ID：{}，错误信息：{}", message, e.getMessage());
            }

            if ( sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK ) {
                log.info("订单延迟消息发送成功，订单ID：{}", message); break;
            } else {
                log.error("订单延迟消息发送失败，订单ID：{}", message);
                // 可以考虑将此消息存起来，或者重试.....
            }

            retryCount++;
        } while (sendResult == null && retryCount < 3);
    }
}
