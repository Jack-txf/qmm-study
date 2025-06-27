package com.feng.mq.controller;

import com.feng.mq.common.R;
import com.feng.mq.rocketmq.producer1.OrderExpireProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/6/27 14:14
 * @注释 订单超时关闭场景
 */
@RestController
@RequestMapping("/order")
public class OrderExpireController {
    @Resource
    private OrderExpireProducer orderExpireProducer;

    @PostMapping("/orderSubmit")
    public R orderSubmit(@RequestParam("orderId") String orderId) {
        // 模拟订单超时关闭
        // 1.mq发送一条延迟消息，延迟时间60秒
        orderExpireProducer.sendOrderExpireMessage(orderId, 20);
        return R.success().setData("msg", "订单提交成功，请耐心等待");
    }
}
