package com.feng.mq.sdksimple.producer;

import com.feng.mq.sdksimple.Topic;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/5/22 14:32
 * @注释 顺序消息生产者
 */
public class OrderMsgProducer {
    public static void main(String[] args) {
        try {
            sendOrderMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendOrderMessage() throws Exception {
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8080;xxx:8081。
        String endpoint = "192.168.110.128:8081";
        // 寝室测试ip
        //String endpoint = "192.168.32.128:8081";
        // 消息发送的目标Topic名称，需要提前创建。
        String topic = Topic.ORDER_TOPIC;

        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint)
                .enableSsl(false);
        ClientConfiguration configuration = builder.build();
        // 初始化Producer时需要设置通信配置以及预绑定的Topic。
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();
        try {
            // 发送消息，需要关注发送结果，并捕获失败等异常。
            for (int i = 0; i < 10; i++) {
                // 普通消息发送。
                Message message = provider.newMessageBuilder()
                        .setTopic(topic)
                        // 设置消息索引键，可根据关键字精确查找某条消息。
                        .setKeys("order-messageKey" + i)
                        // 设置消息Tag，用于消费端根据指定Tag过滤消息。
                        .setTag("order-messageTag" + i )
                        //设置顺序消息的排序分组，该分组尽量保持离散，避免热点排序分组。
                        .setMessageGroup("fifoGroup001")
                        // 消息体。
                        .setBody(("我是顺序消息体 " + i).getBytes())
                        .build();
                SendReceipt sendReceipt = producer.send(message);
                System.out.println("Send message successfully, messageId=: "+  sendReceipt.getMessageId());
            }
        } catch (ClientException e) {
            System.out.println("Failed to send message" + e);;
        }
        producer.close();
    }
}
