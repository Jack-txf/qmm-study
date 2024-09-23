package com.feng.report.wxShopTable;

import com.alibaba.excel.EasyExcel;
import com.feng.report.util.ShowResultUtil;
import com.feng.report.wxShopTable.entity.DaySituation;
import com.feng.report.wxShopTable.entity.WxRealObject;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*
如果是csv格式，单元格里面都是String类型的，建议转成xlsx格式！！！！
如果是csv的文件，建议先把编码改为utf-8，将里面的单元格格式设置好。

wexin-0825-0913.xlsx
 */
public class WxExcelUtils { // 微信商家导出的表，解析工具
     // private static final String PATTERN_YYYY_MM_DD = "yyyy/MM/dd HH:mm:ss";
     // private static final SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);

    private static List<WxRealObject> readWxCsv( String excelPath ) {
        File file = new File(excelPath);
        WxReadListener wxReadListener = new WxReadListener();

        EasyExcel.read(file, WxRealObject.class, wxReadListener)
                .charset(StandardCharsets.UTF_8).sheet(0)
                .doRead();
        return wxReadListener.getDataList();
    }

    /* 分析数据
        0.统计
        1. 根据”商品名称“统计 每个名称的情况 -- 其中又分为SUCCESS和REFUND两种状态
     */
    public static Map<String, Object> analyseData(String excelPath) {
        List<WxRealObject> dataList = readWxCsv(excelPath); // 读取到数据
        // 初始化值
        BigDecimal pay = BigDecimal.valueOf(0);
        BigDecimal refund = BigDecimal.valueOf(0);
        // key-月份   value-统计对象
        TreeMap<Integer, DaySituation> mon_day_sit_pay = new TreeMap<>();
        TreeMap<Integer, DaySituation> mon_day_sit_refund = new TreeMap<>();
        // 遍历读取的数据，进行统计-------------
        for (WxRealObject dayData : dataList) {
            String goodsName = dayData.getGoodsName();
            // 只统计非大客户的!!
            // 三种商品名称需要统计 ：“阅淘网微端订单”， “阅淘网APP订单”，“微信扫码订单”（大客户的不统计）
            if ( goodsName != null && !goodsName.contains("大客户") && goodsName.contains("阅淘网") || goodsName.contains("微信扫码")) {
                //==== 获取年月日
                Calendar calendar = Calendar.getInstance();
                Date tradeTime = dayData.getTradeTime();
                calendar.setTime(tradeTime);
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH) + 1;
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                boolean newCreate_pay = false;
                boolean newCreate_refund = false;
                if (!mon_day_sit_pay.containsKey(m))  // 如果这个月出现过了
                    newCreate_pay = true;
                if ( !mon_day_sit_refund.containsKey(m))
                    newCreate_refund = true;
                //======
                if ( dayData.getTradeStatus().equals("SUCCESS") ) { // 支付
                    pay = pay.add(BigDecimal.valueOf(dayData.getShouldOrderNumber()));
                    if ( newCreate_pay ) { // 如果需要新建对象
                        DaySituation daySituation = DaySituation.success(y, m);
                        mon_day_sit_pay.put(m, daySituation);
                    }
                    DaySituation situation = mon_day_sit_pay.get(m);
                    BigDecimal[] total = situation.getTotal();
                    total[d] = total[d].add(BigDecimal.valueOf(dayData.getShouldOrderNumber()));
                }
                else  { // 退款的
                    refund = refund.add(BigDecimal.valueOf(dayData.getRefundMoney()));
                    if ( newCreate_refund ) { // 如果需要新建对象
                        DaySituation daySituation = DaySituation.refund(y, m);
                        mon_day_sit_refund.put(m, daySituation);
                    }
                    DaySituation situation = mon_day_sit_refund.get(m);
                    BigDecimal[] total = situation.getTotal();
                    total[d] = total[d].add(BigDecimal.valueOf(dayData.getRefundMoney()));
                }

            }
        }

        // 返回结果
        HashMap<String, Object> map = new HashMap<>();
        map.put("total_pay", pay);
        map.put("total_refund", refund);
        map.put("situation_pay", mon_day_sit_pay);
        map.put("situation_refund", mon_day_sit_refund);
        // 显示结果
        ShowResultUtil.showResult(pay, refund, mon_day_sit_pay, mon_day_sit_refund);
        return map;
    }

}
