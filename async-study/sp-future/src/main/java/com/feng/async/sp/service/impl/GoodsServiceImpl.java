package com.feng.async.sp.service.impl;

import com.feng.async.sp.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Override
    public String getDescribeMsg(String goodsId) {
        try {
            TimeUnit.SECONDS.sleep(1); // 模拟查询
            Random random = new Random();
            int nextInt = random.nextInt(100);
            return "详细信息：" + nextInt;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
