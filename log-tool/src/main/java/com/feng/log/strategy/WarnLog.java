package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.pojo.R;

// warn类型日志解析
public class WarnLog implements LogStrategy{
    @Override
    public R tackle(YmlConfig ymlConfig) {

        return R.success().setData("msg", "WARN");
    }
}
