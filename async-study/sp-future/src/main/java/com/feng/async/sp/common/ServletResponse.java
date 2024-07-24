package com.feng.async.sp.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServletResponse {
    private Integer code;
    private String msg;
    private Map<String, Object> data;
}
