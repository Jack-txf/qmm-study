package com.feng.mq.controller;

import com.feng.mq.common.R;
import com.feng.mq.idempotency.IdempotentProducer;
import com.feng.mq.rocketmq.producer1.RocketMQProducer1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rocket")
public class MqController {
    @Resource
    private RocketMQProducer1 rocketMQProducer1;
    @Resource
    private IdempotentProducer idempotentProducer;

    @PostMapping("/sendMsgNormal")
    public R sendMsgNormal(@RequestBody String msg) {
        rocketMQProducer1.sendSyncMessage(msg); // 发送同步消息
        return R.success().setData("msg", "ok");
    }

    // 消费者可靠性测试
    @PostMapping("/sendMsgReliability")
    public R sendMsgReliability(@RequestBody String orderId) {
        rocketMQProducer1.sendReliabilityOrderTest(orderId);
        return R.success().setData("msg", "ok");
    }

    // 消息幂等性
    @PostMapping("/sendMsgIdempotent")
    public R sendMsgIdempotent(@RequestBody String msg) {
        idempotentProducer.sendNormalMessage( msg);
        return R.success().setData("msg", "ok");
    }
}
