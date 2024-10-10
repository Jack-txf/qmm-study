package com.feng.report.wxShopTable.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.feng.report.wxShopTable.convertor.DateConverter;
import lombok.Data;
import java.util.Date;
@Data
public class WxRealObject {

    @DateTimeFormat(value = "yyyy/MM/dd HH:mm:ss")
    @ExcelProperty(value = "交易时间", converter = DateConverter.class)
    private Date tradeTime; // 交易时间=============

    private String publicAccountId; // 公众账号ID
    private String shopNo; // 商户号
    private String teYue;
    private String deviceNo;
    private String wxOrderNo;
    private String shopOrderNo; // 商户订单号
    private String userSign;
    private String tradeClass;
    private String tradeStatus; // 交易状态
    private String bank;
    private String moneyClass;
    private Double shouldOrderNumber; // 应结订单金额================
    private String coupon;
    private String wxRefundNo;
    private String shopRefundNo;
    private Double refundMoney; // 退款金额===============
    private String chongZhiRefund;
    private String refundClass;
    private String refundStatus;
    private String goodsName; // 商品名称
    private String shopDataPackage;
    private String handMoney;
    private String moneyRate;
    private String orderNumber; // 订单金额
    private String applyRefundMoney;
    private String rateMsg;
}
