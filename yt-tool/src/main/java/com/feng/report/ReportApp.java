package com.feng.report;

import com.feng.report.wxShopTable.WxExcelUtils;
import com.feng.report.ytwTable.YtwExcelUtils;

import java.util.Map;
/*
前提文件处理：(记得删除掉没有用的行，例如统计行)
1. 微信商家文件：记得将csv先转为xlsx格式，然后全选，单元格设置为文本格式，替换掉所有的 ` 字符，然后应结订单金额和退款金额设置为数字格式。
 */
public class ReportApp {
    public static void main(String[] args) {
        // 指定文件的位置（相对于此电脑）
        // String excelPath = "D:\\A_yuetao\\report\\wxShop-0914-0917 - 副本.xlsx";
        // Map<String, Object> res = WxExcelUtils.analyseData(excelPath);

        String excelPath = "D:\\A_yuetao\\2024-9月份对账-0825-0917-大致齐的\\0918对账-0914-0917\\ytw&dp-wx-0914-0917.xlsx";
        Object res = YtwExcelUtils.analyseYtwExcel(excelPath);
    }
}
