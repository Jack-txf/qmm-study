package com.feng.mq.sdksimple.consumer;

import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.MessageListener;
import org.apache.rocketmq.client.apis.consumer.SimpleConsumer;
import org.apache.rocketmq.client.apis.consumer.SimpleConsumerBuilder;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.java.impl.consumer.SimpleConsumerBuilderImpl;

import java.time.Duration;
import java.util.List;

/**
 * @Description:
 * @Author: txf
 * @Date: 2025/5/20
 */
public class MySimpleConsumer {
    public static void main(String[] args) throws ClientException {
        SimpleConsumerBuilder b = new SimpleConsumerBuilderImpl();
        SimpleConsumer simpleConsumer = b.build();
        //消费示例一：使用PushConsumer消费普通消息，只需要在消费监听器中处理即可。
        MessageListener messageListener = new MessageListener() {
            @Override
            public ConsumeResult consume(MessageView messageView) {
                System.out.println(messageView);
                //根据消费结果返回状态。
                return ConsumeResult.SUCCESS;
            }
        };
        //消费示例二：使用SimpleConsumer消费普通消息，主动获取消息进行消费处理并提交消费结果。
        List<MessageView> messageViewList = null;
        try {
            messageViewList = simpleConsumer.receive(10, Duration.ofSeconds(30));
            messageViewList.forEach(messageView -> {
                System.out.println(messageView);
                //消费处理完成后，需要主动调用ACK提交消费结果。
                try {
                    simpleConsumer.ack(messageView);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClientException e) {
            //如果遇到系统流控等原因造成拉取失败，需要重新发起获取消息请求。
            e.printStackTrace();
        }

    }
}
