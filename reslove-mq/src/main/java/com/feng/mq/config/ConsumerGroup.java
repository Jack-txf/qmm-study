package com.feng.mq.config;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/5/22 16:31
 * @注释 消费者组
 */
public class ConsumerGroup {
    public static final String SP_Normal_ConsumerGroup = "sp-normal-consumer-group"; // 普通消费组
    public static final String SP_Order_ConsumerGroup = "sp-order-consumer-group"; // 顺序消费组
    public static final String SP_Delay_ConsumerGroup = "sp-delay-consumer-group"; // 延时消费组
    public static final String SP_Transaction_ConsumerGroup = "sp-transaction-consumer-group"; // 事务消费组

    //------------
    public static final String ORDER_EXPIRE_GROUP = "order-expire-group"; // 订单过期消费组 --- 消费延时消息
    public static final String CONSUMER_RELIABILITY = "reliability-order-group"; // 可靠消费组---普通消息
    public static final String CONSUMER_RELIABILITY_DLQ = "reliability-orderDLQ-group"; // 可靠消费组---死信队列
    public static final String IDEMPOTENT_GROUP = "idempotent-group"; // 幂等消费组 --- 消费普通消息
}
