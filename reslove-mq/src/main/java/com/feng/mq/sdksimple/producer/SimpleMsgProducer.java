package com.feng.mq.sdksimple.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.ProducerBuilder;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.java.impl.producer.ProducerBuilderImpl;
import org.apache.rocketmq.client.java.message.MessageBuilderImpl;

import java.io.IOException;

/**
 * @Description: 普通消息生产者
 * @Author: txf
 * @Date: 2025/5/20
 */
@Slf4j
public class SimpleMsgProducer {
    public static void main(String[] args) {

    }
    // 普通消息发送
    public void sendSyncMessage() throws ClientException, IOException {
        ProducerBuilder p = new ProducerBuilderImpl();
        Producer producer = p.build();

        MessageBuilder messageBuilder = new MessageBuilderImpl();
        Message message = messageBuilder.setTopic("topic")
                //设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys("messageKey")
                //设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("messageTag")
                //消息体。
                .setBody("messageBody".getBytes())
                .build();
        try {
            //发送消息，需要关注发送结果，并捕获失败等异常。
            SendReceipt sendReceipt = producer.send(message);
            System.out.println(sendReceipt.getMessageId());
        } catch (ClientException e) {
            e.printStackTrace();
        }

        producer.close();
    }
}
