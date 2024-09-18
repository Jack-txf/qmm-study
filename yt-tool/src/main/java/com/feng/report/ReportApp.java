package com.feng.report;


import com.feng.report.wxShopTable.WxExcelUtils;
import com.feng.report.wxShopTable.entity.WxRealObject;

import java.util.List;

/*
csv文件记得将其编码转为utf-8！！！！！！！！！！！！！！
 */
public class ReportApp {
    public static void main(String[] args) {
        // 指定文件的位置（相对于此电脑）
        String excelPath = "E:\\a_财务报表文档\\2024-9月份对账-0825-0913-大致齐的\\0918对账-0914-0917\\wxShop-0914-0917.xlsx";
        Object o = WxExcelUtils.analyseData(excelPath);
        System.out.println(o);
    }
}
