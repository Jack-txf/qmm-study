package com.feng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.entity.Trans;
import com.feng.chat.mapper.TransMapper;
import com.feng.chat.service.TransService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
@Service("transService")
public class TransServiceImpl extends ServiceImpl<TransMapper, Trans> implements TransService {
    @Resource
    private TransMapper transMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void simple() {
        Trans trans = new Trans();
        trans.setMsg("1111111111");
        trans.setDescribeMsg("2222222222");
        transMapper.insert(trans);
        int i = 1 / 0;

        Trans trans1 = new Trans();
        trans1.setMsg("1111111111");
        trans1.setDescribeMsg("2222222222");
        transMapper.insert(trans1);
    }

    @Override
    @Transactional
    public void simplet() {
        Trans trans = new Trans();
        trans.setMsg("22222222222@@");
        trans.setDescribeMsg("22222222222@@");
        transMapper.insert(trans);

        simple(); // 套了这个
    }

    @Override
    public void simpled() { // 事务会失效！！！！！
        simple();
        insert();
    }

    @Transactional
    @Override
    public void insert() {
        Trans trans = new Trans();
        trans.setMsg("3333333@@");
        trans.setDescribeMsg("3333333@@");
        transMapper.insert(trans);
    }
}

/*
事务失效：
1.spring要求被代理方法必须得是public的。

2.方法用final修饰，不会生效.可能会知道spring事务底层使用了aop，也就是通过jdk动态代理或者cglib，帮我们生成了代理类，
在代理类中实现的事务功能。但如果某个方法用final修饰了，那么在它的代理类中，就无法重写该方法，也就无法添加事务功能。
如果某个方法是static的，同样无法通过动态代理

3..同一个类中的方法直接内部调用，会导致事务失效

 */
