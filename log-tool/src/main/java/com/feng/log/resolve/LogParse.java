package com.feng.log.resolve;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.log.config.YmlConfig;
import com.feng.log.config.YmlConfig1;
import com.feng.log.myenum.LogType;
import com.feng.log.pojo.R;
import com.feng.log.strategy.LogStrategyFactory;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Data
public class LogParse {
    private YmlConfig ymlConfig;

    public LogParse() {
        this.ymlConfig = initConfig();
        // 初始化策略工厂
        try {
            LogStrategyFactory.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static YmlConfig initConfig() {
        InputStream resource = YmlConfig1.class.getClassLoader().getResourceAsStream("log.yaml");
        if ( resource != null ) {
            Yaml yaml = new Yaml();
            Map<String, Object> load = yaml.load(resource);
            String jsonString = JSONObject.toJSONString(load.get("txflog"));
            YmlConfig1 config1 = JSON.parseObject(jsonString, YmlConfig1.class);
            YmlConfig config = new YmlConfig(config1.getLocation(), config1.getName(), null);
            switch ( config1.getType() ) {
                case "warn":
                    config.setLogType(LogType.WARN);
                    break;
                case "debug":
                    config.setLogType(LogType.DEBUG);
                    break;
                default:
                    config.setLogType(LogType.INFO);
                    break;
            }
            return config;
        }
        return null;
    }

    public R solve() {
        return LogStrategyFactory.solve(ymlConfig);
    }

    public void analyRes(R res) {
        // 打印结果
        List<String> ana = (List<String>) res.getData().get("ana");
        List<Long> big = (List<Long>) res.getData().get("big");

        System.out.println("执行记录条数：" + ana.size());
        //ana.forEach(System.out::println);
        System.out.println("=====耗时的记录=====");
        big.forEach(item -> {
            if ( item > 1500 ) {
                System.err.println("execute time: " + item + " ms");
            }
        });
    }
}
