package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.pojo.R;

public interface LogStrategy {
    R tackle(YmlConfig ymlConfig);
}
