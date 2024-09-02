package com.feng.log.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YmlConfig1 {
    private String location;
    private String name;
    private String type;
}
