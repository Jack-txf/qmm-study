package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.pojo.R;

public class DebugLog implements LogStrategy{
    @Override
    public R tackle(YmlConfig ymlConfig) {
        return R.success().setData("msg", "DEBUG");
    }
}
