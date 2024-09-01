package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.myenum.LogType;
import com.feng.log.pojo.R;

public class InfoLog implements LogStrategy{
    @Override
    public R tackle(YmlConfig ymlConfig) {
        return R.success().setData("msg", "INFO");
    }
}
