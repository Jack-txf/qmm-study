package com.feng.compare;

import com.feng.compare.obj.DayTotal;
import com.feng.compare.obj.ExcelData;
import com.feng.compare.obj.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reslover {
    // 1. 解析每一天的订单数，册数，码洋总和，平均订单价格
    private static final Map<String, DayTotal> dayTotalMap = new HashMap<>();

    public static void slove(List<ExcelData> list) {
        // 1. 解析每一天的订单数，册数，码洋总和，平均订单价格
        firstStep(list);
        // 2.看占比
        secondStep(list);
        // 3.看这个时间段类别分布
        thirdStep(list);
    }

    private static void thirdStep(List<ExcelData> list) {
        String max = "0000-00-00", min = "9999-99-99";
        HashMap<String, Pair> map = new HashMap<>();
        for (ExcelData data : list) {
            if (data.getDayTime().compareTo(max) > 0) max = data.getDayTime();
            if (data.getDayTime().compareTo(min) < 0) min = data.getDayTime();
            //
            if (map.containsKey(data.getName())) {
                Pair pair = map.get(data.getName());
                pair.addNums(1);
                pair.addMayang(data.getMayang());
                map.put(data.getName(), pair);
            } else {
                map.put(data.getName(), new Pair(1, data.getMayang()));
            }
        }
        // 显示一下
        System.out.println("############" + min + " ~ " + max + "#############");
        int i = 1;
        for (Map.Entry<String, Pair> entry : map.entrySet()) {
            if ( entry.getValue().getNums() >= 150 )  {
                System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getNums() +
                        ": ("+ entry.getValue().getMayang() + ") : 均价："+ BigDecimal.valueOf(entry.getValue().getMayang().doubleValue() / entry.getValue().getNums()).setScale(2, RoundingMode.HALF_UP) +"\t");
                //if (i++ % 2 == 0) {
                //    System.out.println();
                //}
            }
        }
        System.out.println("\n##################################");
    }

    private static void secondStep(List<ExcelData> list) {
        int t = list.size();
        int lt10 = 0, lt30 = 0, lt50 = 0, lt70 = 0, lt90 = 0, lt100 = 0, lt150 = 0, lt200 = 0, lt500 = 0, lt1000 = 0, other = 0;
        String max = "0000-00-00", min = "9999-99-99";
        for (ExcelData data : list) {
            if (data.getDayTime().compareTo(max) > 0) {
                max = data.getDayTime();
            }
            if (data.getDayTime().compareTo(min) < 0) {
                min = data.getDayTime();
            }

            if (data.getMayang() < 10) {
                lt10++;
            } else if (data.getMayang() < 30) {
                lt30++;
            } else if (data.getMayang() < 50) {
                lt50++;
            } else if (data.getMayang() < 70) {
                lt70++;
            } else if (data.getMayang() < 90) {
                lt90++;
            } else if (data.getMayang() <100) {
                lt100++;
            } else if (data.getMayang() < 150) {
                lt150++;
            } else if (data.getMayang() < 200) {
                lt200++;
            } else if (data.getMayang() < 500) {
                lt500++;
            } else if (data.getMayang() < 1000) {
                lt1000++;
            } else {
                other++;
            }
        }
        System.out.println("@@@@@@@@@@@@@【" + min + " ~ " + max + "，价格占比】@@@@@@@@@@@@@@");
        // 显示两位小数
        System.out.printf("\t10元以下占比：%.2f %%\n", (lt10 * 1.0 / t) * 100);
        System.out.printf("\t10-30元占比：%.2f %%\n" , (lt30 * 1.0 / t) * 100);
        System.out.printf("\t30-50元占比：%.2f %%\n" , (lt50 * 1.0 / t) * 100);
        System.out.printf("\t50-70元占比：%.2f %%\n" , (lt70 * 1.0 / t) * 100);
        System.out.printf("\t70-90元占比：%.2f %%\n" , (lt90 * 1.0 / t) * 100);
        System.out.printf("\t90-100元占比：%.2f %%\n" , (lt100 * 1.0 / t) * 100 );
        System.out.printf("\t100-150元占比：%.2f %%\n" , (lt150 * 1.0 / t) * 100);
        System.out.printf("\t150-200元占比：%.2f %%\n" , (lt200 * 1.0 / t));
        System.out.printf("\t200-500元占比：%.2f %%\n" , (lt500 * 1.0 / t) * 100);
        System.out.printf("\t500-1000元占比：%.2f %%\n" , (lt1000 * 1.0 / t) * 100);
        System.out.printf("\t1000元以上占比：%.2f %%\n" , (other * 1.0 / t) * 100);
        System.out.printf("@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
    }

    private static void firstStep(List<ExcelData> list) {
        for (ExcelData data : list) {
            String dayTime = data.getDayTime();
            if (dayTotalMap.containsKey(dayTime)) { // 如果当前日期存在, 加进去
                DayTotal dayTotal = dayTotalMap.get(dayTime);
                dayTotal.addOrderNum(data.getOrderMasterSn()); // 订单数加一下
                dayTotal.addBookNum(data.getProductQuantity()); // 册数加一下
                dayTotal.addMayang(data.getMayang()); // 码洋加一下, 顺便把均价算了
                dayTotalMap.put(dayTime, dayTotal);
            } else { // 如果当前日期不存在
                DayTotal dayTotal = new DayTotal(null, data.getProductQuantity(), data.getMayang(), data.getMayang() / data.getProductQuantity());
                dayTotalMap.put(dayTime, dayTotal);
            }
        }
        // 显示一下
        for (Map.Entry<String, DayTotal> entry : dayTotalMap.entrySet()) {
            System.out.println("============"+ entry.getKey() +"===============");
            System.out.println(entry.getValue());
            System.out.println("===========================\n");
        }
    }
}
