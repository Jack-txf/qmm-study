package com.feng.log.resolve;

import com.feng.log.config.YmlConfig;
import com.feng.log.myenum.LogType;
import com.feng.log.pojo.R;
import com.feng.log.strategy.LogStrategy;
import com.feng.log.strategy.LogStrategyFactory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogParse {
    private YmlConfig ymlConfig;

    public LogParse(YmlConfig ymlConfig) {
        this.ymlConfig = ymlConfig;
        // 初始化策略工厂
        try {
            LogStrategyFactory.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public R solve() {
        return LogStrategyFactory.solve(ymlConfig);
    }
}
