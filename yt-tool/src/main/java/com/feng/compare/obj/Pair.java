package com.feng.compare.obj;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Pair {
    private Integer nums;
    private Double mayang;

    public Pair(Integer nums, Double mayang)
    {
        this.nums = nums;
        this.mayang = mayang;
    }

    public BigDecimal getMayang() {
        return BigDecimal.valueOf(mayang).setScale(2, RoundingMode.HALF_UP);
    }

    public void addMayang(Double mayang) {
        this.mayang += mayang;
    }

    public void addNums(Integer nums) {
        this.nums += nums;
    }
}
