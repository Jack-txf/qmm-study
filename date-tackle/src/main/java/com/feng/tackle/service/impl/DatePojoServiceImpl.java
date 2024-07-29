package com.feng.tackle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.tackle.dao.DatePojoDao;
import com.feng.tackle.entity.DatePojo;
import com.feng.tackle.service.DatePojoService;
import org.springframework.stereotype.Service;

/**
 * (DateTab)表服务实现类
 *
 * @author makejava
 * @since 2024-07-29 20:03:48
 */
@Service("datePojoService")
public class DatePojoServiceImpl extends ServiceImpl<DatePojoDao, DatePojo> implements DatePojoService {

}

