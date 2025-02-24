package com.feng.quxian.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/24 15:31
 * @注释 写入excel的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritePojo {

    @ExcelProperty(value = "店铺名称")
    private String storeName;

    @ExcelProperty(value = "分类")
    private String categoryName;

    @ExcelProperty(value = "码洋")
    private BigDecimal maYang;

    @ExcelProperty(value = "实洋")
    private BigDecimal shiYang;
}
