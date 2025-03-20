package com.feng.obj;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/3/20 10:39
 * @注释
 */
@Component
public class UseObj {
    @Resource
    private Config1 config1;
    //
    //@Setter
    //@Getter
    //private String name = config1.getName();
    //
    //public UseObj() {
    //    System.out.println("UseObj无参构造");
    //}

}
