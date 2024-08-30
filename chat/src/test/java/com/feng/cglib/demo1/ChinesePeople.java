package com.feng.cglib.demo1;

// 被代理类
public class ChinesePeople {

    public String person(String arg1, String arg2) {
        if (arg1.equals("青莲地心火") && arg2.equals("陨落心炎")) {
            System.out.println("斗破苍穹---萧炎");
            return "萧炎";
        }
        return "123";
    }
}