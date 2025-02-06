package com.feng.compare.obj;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelData {
    @ExcelProperty("dayTime")
     private String dayTime;

     @ExcelProperty("orderMasterSn")
     private String orderMasterSn;

    @ExcelProperty("goodsId")
     private String goodsId;

     @ExcelProperty("goodsName")
     private String goodsName;

     @ExcelProperty("name")
     private String name;

    @ExcelProperty("originalPrice")
     private Double originalPrice;

     @ExcelProperty("productQuantity")
     private Integer productQuantity;

     @ExcelProperty("mayang")
     private Double mayang;

    @ExcelProperty("storeId")
     private String storeId;

     @ExcelProperty("largeCustomerFlag")
     private String largeCustomerFlag;

     @ExcelProperty("receptionMethod")
     private String receptionMethod;
}
