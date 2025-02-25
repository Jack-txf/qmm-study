package com.feng.quxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/24 15:00
 * @注释 excel的行对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelRow {
    private String user_id;
    private String storeName;
    private String orderMasterSn;
    private String goodsId;
    private String goodsName;
    private String name; // 类别
    private Integer product_quantity;
    private Double original_price;
    private Double sell_price;
}
