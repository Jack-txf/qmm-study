package com.feng.mq.config;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/5/22 16:29
 * @注释 主题常量
 */
public class TopicConstant {
    public static final String SP_Normal_Topic = "sp-normal-topic"; // 普通消息
    public static final String SP_Order_Topic = "sp-order-topic"; // 顺序消息
    public static final String SP_Delay_Topic = "sp-delay-topic"; // 延时消息
    public static final String SP_Transaction_Topic = "sp-transaction-topic"; // 事务消息

    //============
    public static final String ORDER_EXPIRE_TOPIC = "order-expire-topic"; // 订单过期的topic
    public static final String CONSUMER_RELIABILITY_TOPIC = "reliability-order-topic"; // 消费者可靠性topic--普通消息
}
