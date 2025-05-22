package com.feng.mq.sdksimple.consumer;

import com.feng.mq.sdksimple.Topic;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;

/**
 * @version 1.0
 * @Author
 * @Date 2025/5/22 11:39
 * @注释
 */
public class DelayConsumer {
    public static void main(String[] args) {
        try {
            simpleDelayConsumer();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public static void simpleDelayConsumer() throws ClientException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081。
        String endpoints = "192.168.110.128:8081";
        // 寝室测试ip
        //String endpoints = "192.168.32.128:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        // 订阅消息的过滤规则，表示订阅所有Tag的消息。
        String tag = "*";
        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
        // 为消费者指定所属的消费者分组，Group需要提前创建。
        String consumerGroup = "Delay-Consumer-Group";
        // 指定需要订阅哪个目标Topic，Topic需要提前创建。
        String topic = Topic.DELAY_TOPIC;
        // 初始化PushConsumer，需要绑定消费者分组ConsumerGroup、通信参数以及订阅关系。
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                // 设置消费者分组。
                .setConsumerGroup(consumerGroup)
                // 设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                // 设置消费监听器。
                .setMessageListener(messageView -> {
                    // 处理消息并返回消费结果。
                    ByteBuffer body = messageView.getBody();
                    int remaining = body.remaining();
                    byte[] content = new byte[remaining];
                    body.get(content);
                    String str = new String(content);

                    System.out.println("Consume delay message successfully, messageId=" + messageView.getMessageId());
                    System.out.println("Receive message: " + str);
                    System.out.println(messageView.getDeliveryTimestamp());
                    return ConsumeResult.SUCCESS;
                })
                .build();
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // 如果不需要再使用 PushConsumer，可关闭该实例。
            pushConsumer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
