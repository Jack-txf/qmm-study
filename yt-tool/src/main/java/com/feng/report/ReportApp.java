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
        // 指定文件的位置（相对于此电脑）---------- 微信商家
         String excelPath1 = "E:\\a_财务报表文档\\2024-9月份对账-0825-0919-大致齐的\\20240920对账-0918-0919\\wxShop-0918-0919.xlsx";
         Map<String, Object> res = WxExcelUtils.analyseData(excelPath1);

        System.out.println("\n##################################################################\n");

         // 指定文件的位置（相对于此电脑）---------- ytw微信支付
        String excelPath = "E:\\a_财务报表文档\\2024-9月份对账-0825-0919-大致齐的\\20240920对账-0918-0919\\ytw&dp-wx-0918-0919.xlsx";
        Object res2 = YtwExcelUtils.analyseYtwExcel(excelPath);
    }
}
