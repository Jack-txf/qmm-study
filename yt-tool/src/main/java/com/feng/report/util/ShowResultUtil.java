package com.feng.report.util;

import com.feng.report.wxShopTable.entity.DaySituation;

import java.math.BigDecimal;
import java.util.TreeMap;

public class ShowResultUtil {
    public static void showResult(BigDecimal pay, BigDecimal refund, TreeMap<Integer, DaySituation> mon_day_sit_pay, TreeMap<Integer, DaySituation> mon_day_sit_refund) {
        // 算出起止时间
        DaySituation start = mon_day_sit_pay.get(mon_day_sit_pay.firstKey());
        DaySituation end = mon_day_sit_pay.get(mon_day_sit_pay.lastKey());
        int stm = start.getMonth();
        int endm = end.getMonth();
        int sd = 0, ed = 0;
        for (int i = 1; i < start.getTotal().length; ++i)
            if ( !start.getTotal()[i].equals(BigDecimal.ZERO) ) { sd = i; break;}
        for (int i = end.getTotal().length - 1; i >= 1; --i)
            if ( !end.getTotal()[i].equals(BigDecimal.ZERO) ) { ed = i; break;}
        String desc = stm + "月" + sd + "日 -- " + endm + "月" + ed + "日 ";

        System.out.println(desc + "【支付 总计】: " + pay);
        System.out.println(desc + "【退款 总计】: " + refund);
        System.out.println("================================支付情况");
        mon_day_sit_pay.forEach( (key, value) -> System.out.println(value));
        System.out.println("================================退款情况");
        mon_day_sit_refund.forEach( (key, value) -> System.out.println(value));
    }
}
