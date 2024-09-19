package com.feng.report.wxShopTable.entity;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

// month total[i] 表示 month月i日 当天总计
public class DaySituation {
    private static final int[] monDays = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static boolean isRunYear( int year ) {
        return ( year % 400 == 0 ) || ( year % 4 == 0 && year % 100 != 0);
    }

    private String Status;
    @Getter
    private Integer month;
    @Getter
    private BigDecimal[] total;

    public static DaySituation success( int year, int month ) {
        DaySituation daySituation = new DaySituation(year, month);
        daySituation.setStatus("SUCCESS");
        return daySituation;
    }
    public static DaySituation refund( int year, int month ) {
        DaySituation daySituation = new DaySituation(year, month);
        daySituation.setStatus("REFUND");
        return daySituation;
    }
    private DaySituation(int year, int month) {
        if ( month < 1 || month > 12 ) throw new RuntimeException("月份不正确!");
        this.month = month;
        int days = monDays[month];
        if (isRunYear(year) && month == 2 ) days++;
        this.total = new BigDecimal[days+1];
        Arrays.fill(total, BigDecimal.ZERO);
    }
    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        StringBuilder str1 = new StringBuilder("========" + this.month + "月(start)=========\n");
        for (int i = 0; i < total.length; i++) {
            if ( total[i].equals(BigDecimal.ZERO) ) continue;
            if ( this.Status.equals("SUCCESS")) { // 支付
                str1.append(i).append("日【支付】：").append(total[i]).append("\n");
            }
            else { // 退款
                str1.append(i).append("日【退款】：").append(total[i]).append("\n");
            }
        }
        str1.append("========").append(this.month).append("月(end)=========");
        return str1.toString();
    }
}
