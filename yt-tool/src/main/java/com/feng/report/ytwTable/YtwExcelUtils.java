package com.feng.report.ytwTable;

import com.alibaba.excel.EasyExcel;
import com.feng.report.util.ShowResultUtil;
import com.feng.report.wxShopTable.entity.DaySituation;
import com.feng.report.ytwTable.eneity.YtwExcelData;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

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
        TreeMap<Integer, DaySituation> pay_sit = new TreeMap<>();
        TreeMap<Integer, DaySituation> refund_sit = new TreeMap<>();
        for (YtwExcelData data : ytwExcelData) {
            String tradeType = data.getTradeType();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data.getTradeTime());
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            if ( tradeType != null && data.getTradeType().contains("退") ) { // 退的
                refund = refund.add(BigDecimal.valueOf(data.getThirdPayment()));
                if ( !refund_sit.containsKey(m) ) refund_sit.put(m ,DaySituation.refund(y, m));
                BigDecimal[] total = refund_sit.get(m).getTotal();
                total[d] = total[d].add(BigDecimal.valueOf(data.getThirdPayment()));
            }
            else {
                pay = pay.add(BigDecimal.valueOf(data.getThirdPayment()));
                if ( !pay_sit.containsKey(m) ) pay_sit.put(m ,DaySituation.success(y, m));
                BigDecimal[] rtotal = pay_sit.get(m).getTotal();
                rtotal[d] = rtotal[d].add(BigDecimal.valueOf(data.getThirdPayment()));
            }
        }
        // 显示结果
        ShowResultUtil.showResult(pay, refund, pay_sit, refund_sit);
        return ytwExcelData;
    }
}
