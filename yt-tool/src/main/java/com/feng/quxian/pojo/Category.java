package com.feng.quxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/24 15:09
 * @注释 类别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String name; // 类别名称
    private int count; // 数量
    private Double maYang;
    private Double shiYang;

    public BigDecimal getMaYang() {
        return BigDecimal.valueOf(maYang).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getShiYang() {
        return BigDecimal.valueOf(shiYang).setScale(2, RoundingMode.HALF_UP);
    }

    public void addMaYang(Double maYang) {
        this.maYang += maYang;
    }

    public void addShiYang(Double shiYang) {
        this.shiYang += shiYang;
    }
}
