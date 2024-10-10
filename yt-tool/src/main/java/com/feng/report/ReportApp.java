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
        //wxShop();

        ytwWx();
    }

    private static void zfbShop() {
        System.out.println("--------------------------------------------------------------------------------------");
        // 指定文件的位置（相对于此电脑）---------- 支付宝商家
        String excelPath1 = "E:\\a_报表\\20241010对账--十月份\\0923-1006\\zfb-shop-0923-1006.xls";

        System.out.println("--------------------------支付宝商家还没做！--------------------------------------------------------");
    }
    private static void wxShop() {
        System.out.println("--------------------------------------------------------------------------------------");
        // 指定文件的位置（相对于此电脑）---------- 微信商家
        String excelPath = "E:\\a_报表\\20241010对账--十月份\\0923-1006\\wx-shop-0923-1006.xlsx";
        Map<String, Object> map = WxExcelUtils.analyseData(excelPath);
        System.out.println("--------------------------------------------------------------------------------------");
    }
    private static void ytwWx() {
        System.out.println("--------------------------------------------------------------------------------------");
        YtwExcelUtils.analyseYtwExcel("E:\\a_报表\\20241010对账--十月份\\0923-1006\\ytw&dp-wx-0923-1006.xlsx");
        System.out.println("--------------------------------------------------------------------------------------");
    }
    private static void ytwZfb() {
        System.out.println("--------------------------------------------------------------------------------------");
        YtwExcelUtils.analyseYtwExcel("E:\\a_报表\\20241010对账--十月份\\0923-1006\\ytw&dp-zfb-0923-1006.xlsx");
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
