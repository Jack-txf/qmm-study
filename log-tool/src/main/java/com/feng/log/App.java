package com.feng.log;

import com.alibaba.fastjson.JSONObject;
import com.feng.log.pojo.R;
import com.feng.log.resolve.LogParse;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // 创建日志解析对象
        LogParse logParse = new LogParse();
        // 解析日志
        R res = logParse.solve();
        // 打印结果
        List<String> ana = (List<String>) res.getData().get("ana");
        List<Long> big = (List<Long>) res.getData().get("big");

        ana.forEach(System.out::println);
        big.forEach(System.out::println);
    }
}
