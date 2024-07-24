package com.feng.async.sp.service.impl;

import com.feng.async.sp.common.exception.MyException;
import com.feng.async.sp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<String> getCategory(String goodsId) {
        LinkedList<String> strings = new LinkedList<>();
        try {
            TimeUnit.SECONDS.sleep(1); // 模拟查询

            Random random = new Random();
            int nextInt = 0;
            for ( int i = 1; i <= 5; ++i ) {
                nextInt = random.nextInt(100);
                strings.add("详细信息：" + nextInt);
            }

            return strings;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> createMyException(String goodsId) {
        int a = 3;
        throw new MyException(4444, "不对哦，小伙子");
    }
}
