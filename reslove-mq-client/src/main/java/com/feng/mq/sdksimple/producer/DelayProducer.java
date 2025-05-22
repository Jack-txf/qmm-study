package com.feng.mq.sdksimple.producer;

import com.feng.mq.sdksimple.Topic;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;

import java.io.IOException;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/5/22 11:29
 * @注释 延时消息
 */
public class DelayProducer {
    public static void main(String[] args) {
        try {
            sendDelayMessage();
        } catch (ClientException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendDelayMessage() throws ClientException, IOException {
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8080;xxx:8081。
        String endpoint = "192.168.110.128:8081";
        // 寝室测试ip
        //String endpoint = "192.168.32.128:8081";
        // 消息发送的目标Topic名称，需要提前创建。
        String topic = Topic.DELAY_TOPIC;

        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint)
                .enableSsl(false);
        ClientConfiguration configuration = builder.build();
        // 初始化Producer时需要设置通信配置以及预绑定的Topic。
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();
        // 普通消息发送。
        //以下示例表示：延迟时间为1分钟之后的Unix时间戳。
        long deliverTimeStamp = System.currentTimeMillis() + 60 * 1000;

        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                // 设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys("delay-messageKey")
                // 设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("delay-messageTag")
                .setDeliveryTimestamp(deliverTimeStamp)
                // 消息体。
                .setBody("我是延时消息消息体".getBytes())
                .build();
        try {
            // 发送消息，需要关注发送结果，并捕获失败等异常。
            SendReceipt sendReceipt = producer.send(message);
            System.out.println("Send delay message ok, messageId=: "+  sendReceipt.getMessageId());
        } catch (ClientException e) {
            System.out.println("Failed to send message" + e);;
        }
        producer.close();
    }
}
