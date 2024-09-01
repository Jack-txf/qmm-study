package com.feng.log;

import com.alibaba.fastjson.JSONObject;
import com.feng.log.config.YmlConfig;
import com.feng.log.myenum.LogType;
import com.feng.log.pojo.R;
import com.feng.log.resolve.LogParse;

public class App {
    public static void main(String[] args) {
        // 从配置文件构建yml配置
        YmlConfig ymlConfig = new YmlConfig();
        ymlConfig.setLogType(LogType.INFO);

        // 创建日志解析对象
        LogParse logParse = new LogParse(ymlConfig);

        // 解析日志
        R res = logParse.solve();

        // 打印结果
        String jsonString = JSONObject.toJSONString(res);
        System.out.println(jsonString);
    }
}
