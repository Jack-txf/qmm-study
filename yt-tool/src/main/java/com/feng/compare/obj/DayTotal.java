package com.feng.compare.obj;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

// 订单数，册数，码洋总和，平均订单价格
@Data
public class DayTotal {
    private Set<String> orderNum;
    private Integer bookNum;
    private Double mayang;
    private Double avgPrice;
    public DayTotal() {
        this.orderNum = new HashSet<>();
        this.bookNum = 0;
        this.mayang = 0.00;
        this.avgPrice = 0.00;
    }
    public DayTotal(Set<String> s ,Integer bookNum, Double mayang, Double avgPrice) {
        this.orderNum = new HashSet<>();
        this.bookNum = bookNum;
        this.mayang = mayang;
        this.avgPrice = avgPrice;
    }

    public void addOrderNum(String masterSn) {
        this.orderNum.add(masterSn);
    }
    public void addBookNum(Integer bookNum) {
        this.bookNum += bookNum;
    }
    public void addMayang(Double mayang) {
        this.mayang += mayang;
        this.avgPrice = this.mayang / this.bookNum;
    }

    public Double getAvgPrice() {
        return BigDecimal.valueOf(avgPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public Double getMayang() {
        return BigDecimal.valueOf(mayang).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    // 重写一下toString方法
    @Override
    public String toString() {
        return "\t订单数=" + orderNum.size() +
                ", \n\t册数=" + bookNum +
                ", \n\t码洋=" + BigDecimal.valueOf(mayang).setScale(2, RoundingMode.HALF_UP) +
                ", \n\t当日均价=" + BigDecimal.valueOf(avgPrice).setScale(2, RoundingMode.HALF_UP);
    }
}
