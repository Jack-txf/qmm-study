package com.feng.report.ytwTable;

import com.alibaba.excel.EasyExcel;
import com.feng.report.ytwTable.eneity.YtwExcelData;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class YtwExcelUtils {
    private static List<YtwExcelData> readYtwData( String excelPath ) {
        YtwListener ytwListener = new YtwListener();
        File file = new File(excelPath);
        EasyExcel.read(file, YtwExcelData.class, ytwListener)
                .charset(StandardCharsets.UTF_8).sheet(0)
                .doRead();
        return ytwListener.getData();
    }

    public static Object analyseYtwExcel( String excelPath ) {
        List<YtwExcelData> ytwExcelData = readYtwData(excelPath); // 读取文件
        BigDecimal refund = BigDecimal.ZERO;
        BigDecimal pay = BigDecimal.ZERO;

        for (YtwExcelData data : ytwExcelData) {
            if ( data.getTradeType().contains("退") ) { // 退的
                refund = refund.add(BigDecimal.valueOf(data.getThirdPayment()));
            }
            else {
                pay = pay.add(BigDecimal.valueOf(data.getThirdPayment()));
            }
        }

        // 显示结果
        showResult(pay, refund);
        return ytwExcelData;
    }

    private static void showResult(BigDecimal pay, BigDecimal refund) {
        System.out.println("【支付 总计】" + pay);
        System.out.println("【退款 总计】" + refund);
    }
}
