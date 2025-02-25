package com.feng.quxian.really;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/25 14:54
 * @注释 最终版的读取excel对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewExcelRow {
    private String user_id;
    private String storeName;
    private String orderMasterSn;
    private String goodsId;
    private String goodsName;
    private String selfCategoryName; // 类别
    private String book_type;
    private String book_class_id;

    private Integer product_quantity;
    private Double original_price;
    private Double sell_price;
}
