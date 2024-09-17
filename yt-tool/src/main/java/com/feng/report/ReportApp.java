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
        String excelPath = "D:\\A_yuetao\\report\\wexin-0825-0913.xlsx";
        List<WxRealObject> wxData = WxExcelUtils.readWxCsv(excelPath);
        // wxData.forEach(System.out::println);
        System.out.println(wxData.size());
    }
}
