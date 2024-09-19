package com.feng.report.ytwTable.eneity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.feng.report.ytwTable.convertor.YtwConvertor;
import lombok.Data;
import java.util.Date;

@Data
public class YtwExcelData {
    private String orderMasterSn;
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "支付/退款时间", converter = YtwConvertor.class)
    private Date tradeTime;
    private Double ytCoin;
    private Double getBookCard;
    private Double thirdPayment; // 三方支付金额
    private Double bigCustomerAccount;
    private String tradeType;
    private String orderDetailId;
    private String goodsNo;
    private String goodsName;
    private String goodsClass;
    private Double maYang;
    private Double shiYang;
    private Integer goodsNumber; // 商品数量
}
