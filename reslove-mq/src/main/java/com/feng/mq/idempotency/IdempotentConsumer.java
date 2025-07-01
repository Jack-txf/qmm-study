package com.feng.mq.idempotency;

import com.feng.mq.config.ConsumerGroup;
import com.feng.mq.config.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/7/1 11:13
 * @注释 幂等性消费者
 */
@Service
@Slf4j
@RocketMQMessageListener(
        topic = TopicConstant.IDEMPOTENT_TOPIC,
        consumerGroup = ConsumerGroup.IDEMPOTENT_GROUP,
        maxReconsumeTimes = 1 // 这里重试一次
)
public class IdempotentConsumer implements RocketMQListener<MessageExt> {
    private final Set<String> msgIds = new HashSet<>(128);
    @Override
    public void onMessage(MessageExt message) {
        String msgId = message.getProperty("msgId"); // 自定义的
        //String msgId1 = message.getMsgId(); // 消息在Broker中的唯一ID，全局唯一，适合做幂等
        String body = new String(message.getBody());
        // 判断消息的唯一性
        if ( judgeUnique(msgId) ) {
            handleMessage(msgId, body);
        }else {
            log.info("消息重复，请勿重复处理");
        }
    }
    private void handleMessage(String msgId, String body) {
        // 处理消息....
        log.info("【幂等性消费者】处理消息: {}", body);
        try {
            TimeUnit.SECONDS.sleep(20); // 模拟处理业务20秒
            log.info("【幂等性消费者】处理消息完成, 入库咯~~~"); // 处理完成之后，msgId可以标识为已处理了
            msgIds.add(msgId);
            /*
            * if ( ok ) msgIds.add(msgId);
            * else msgIds.remove(msgId); // 移除掉
            * */
        } catch (InterruptedException e) {
            log.info("处理消息异常", e);
        }
        throw new RuntimeException("出现异常");
    }
    private boolean judgeUnique(String msgId) {
        // 最简单方式：判断消息的唯一性
        return msgIds.add(msgId);
        /*
        方式二：可以结合中间件Redis来判断
        使用Redis的SETNX命令或Redisson实现分布式锁。消费者获取锁后处理消息，完成后释放锁。
        但是要注意内存问题，如果key过多，可能会内存溢出，建议根据实际情况定期清理key
        */
        // 方式二：使用数据库
    }


    // 没有做幂等校验
    //@Override
    //public void onMessage(MessageExt message) {
    //    String body = new String(message.getBody());
    //    handleMessage(body);
    //}
    //
    //private void handleMessage(String body) {
    //    // 处理消息....
    //    log.info("【幂等性消费者】处理消息中....: {}", body);
    //    try {
    //        TimeUnit.SECONDS.sleep(70); // 模拟处理业务70秒
    //    } catch (InterruptedException e) {
    //        throw new RuntimeException(e);
    //    }
    //    log.info("【幂等性消费者】处理消息完成, 入库咯~~~");
    //    throw new RuntimeException("出现异常");
    //
    //}
}