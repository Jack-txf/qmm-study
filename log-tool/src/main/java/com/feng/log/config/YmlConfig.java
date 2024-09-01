package com.feng.log.config;

import com.feng.log.myenum.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YmlConfig {
    private String location;
    private String name;
    private LogType logType;
}
