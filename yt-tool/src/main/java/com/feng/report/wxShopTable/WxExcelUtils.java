package com.feng.report.wxShopTable;

import com.alibaba.excel.EasyExcel;
import com.feng.report.wxShopTable.entity.WxRealObject;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
如果是csv格式，单元格里面都是String类型的，建议转成xlsx格式！！！！
如果是csv的文件，建议先把编码改为utf-8，将里面的单元格格式设置好。

wexin-0825-0913.xlsx
 */
public class WxExcelUtils { // 微信商家导出的表，解析工具
     private static final String PATTERN_YYYY_MM_DD = "yyyy/MM/dd HH:mm:ss";
     private static final SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);

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
    public static Object analyseData(String excelPath) {
        List<WxRealObject> dataList = readWxCsv(excelPath); // 读取到数据
        // 初始化值
        BigDecimal pay = BigDecimal.valueOf(0);
        BigDecimal refund = BigDecimal.valueOf(0);
        // 遍历读取的数据，进行统计-------------
        for (WxRealObject dayData : dataList) {
            String goodsName = dayData.getGoodsName();
            // 三种商品名称需要统计 ：“阅淘网微端订单”， “阅淘网APP订单”，“微信扫码订单”（大客户的不统计）
            if ( !goodsName.contains("大客户") && goodsName.contains("阅淘") || goodsName.contains("微信")) { // 只统计非大客户的
                if ( dayData.getTradeStatus().equals("SUCCESS") ) pay = pay.add(BigDecimal.valueOf(dayData.getShouldOrderNumber()));
                else  refund = refund.add(BigDecimal.valueOf(dayData.getRefundMoney()));
            }
        }

        // 返回结果
        HashMap<String, Object> map = new HashMap<>();
        map.put("总计【支付】：", pay);
        map.put("总计【退款】：", refund);
        return map;
    }
}
