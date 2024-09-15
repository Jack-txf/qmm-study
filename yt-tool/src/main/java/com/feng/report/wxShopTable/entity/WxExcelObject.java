package com.feng.report.wxShopTable.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 微信商家导出来的表excel每一行数据对象结构
public class WxExcelObject {
    private String tradeTime; // 交易时间=============
    private String publicAccountId; // 公众账号ID
    private String shopNo; // 商户号
    private String teYue;
    private String deviceNo;
    private String wxOrderNo;
    private String shopOrderNo;
    private String userSign;
    private String tradeClass;
    private String tradeStatus;
    private String bank;
    private String moneyClass;
    private String shouldOrderNumber; // 应结订单金额================
    private String coupon;
    private String wxRefundNo;
    private String shopRefundNo;
    private String refundMoney; // 退款金额===============
    private String chongZhiRefund;
    private String refundClass;
    private String refundStatus;
    private String goodsName;
    private String shopDataPackage;
    private String handMoney;
    private String moneyRate;
    private String orderNumber;
    private String applyRefundMoney;
    private String rateMsg;

    public WxExcelObject() {
    }

    public WxExcelObject(String tradeTime, String publicAccountId, String shopNo, String teYue, String deviceNo, String wxOrderNo, String shopOrderNo, String userSign, String tradeClass, String tradeStatus, String bank, String moneyClass, String shouldOrderNumber, String coupon, String wxRefundNo, String shopRefundNo, String refundMoney, String chongZhiRefund, String refundClass, String refundStatus, String goodsName, String shopDataPackage, String handMoney, String moneyRate, String orderNumber, String applyRefundMoney, String rateMsg) {
        this.tradeTime = tradeTime;
        this.publicAccountId = publicAccountId;
        this.shopNo = shopNo;
        this.teYue = teYue;
        this.deviceNo = deviceNo;
        this.wxOrderNo = wxOrderNo;
        this.shopOrderNo = shopOrderNo;
        this.userSign = userSign;
        this.tradeClass = tradeClass;
        this.tradeStatus = tradeStatus;
        this.bank = bank;
        this.moneyClass = moneyClass;
        this.shouldOrderNumber = shouldOrderNumber;
        this.coupon = coupon;
        this.wxRefundNo = wxRefundNo;
        this.shopRefundNo = shopRefundNo;
        this.refundMoney = refundMoney;
        this.chongZhiRefund = chongZhiRefund;
        this.refundClass = refundClass;
        this.refundStatus = refundStatus;
        this.goodsName = goodsName;
        this.shopDataPackage = shopDataPackage;
        this.handMoney = handMoney;
        this.moneyRate = moneyRate;
        this.orderNumber = orderNumber;
        this.applyRefundMoney = applyRefundMoney;
        this.rateMsg = rateMsg;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getPublicAccountId() {
        return publicAccountId;
    }

    public void setPublicAccountId(String publicAccountId) {
        this.publicAccountId = publicAccountId;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getTeYue() {
        return teYue;
    }

    public void setTeYue(String teYue) {
        this.teYue = teYue;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getWxOrderNo() {
        return wxOrderNo;
    }

    public void setWxOrderNo(String wxOrderNo) {
        this.wxOrderNo = wxOrderNo;
    }

    public String getShopOrderNo() {
        return shopOrderNo;
    }

    public void setShopOrderNo(String shopOrderNo) {
        this.shopOrderNo = shopOrderNo;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMoneyClass() {
        return moneyClass;
    }

    public void setMoneyClass(String moneyClass) {
        this.moneyClass = moneyClass;
    }

    public String getShouldOrderNumber() {
        return shouldOrderNumber;
    }

    public void setShouldOrderNumber(String shouldOrderNumber) {
        this.shouldOrderNumber = shouldOrderNumber;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getWxRefundNo() {
        return wxRefundNo;
    }

    public void setWxRefundNo(String wxRefundNo) {
        this.wxRefundNo = wxRefundNo;
    }

    public String getShopRefundNo() {
        return shopRefundNo;
    }

    public void setShopRefundNo(String shopRefundNo) {
        this.shopRefundNo = shopRefundNo;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getChongZhiRefund() {
        return chongZhiRefund;
    }

    public void setChongZhiRefund(String chongZhiRefund) {
        this.chongZhiRefund = chongZhiRefund;
    }

    public String getRefundClass() {
        return refundClass;
    }

    public void setRefundClass(String refundClass) {
        this.refundClass = refundClass;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getShopDataPackage() {
        return shopDataPackage;
    }

    public void setShopDataPackage(String shopDataPackage) {
        this.shopDataPackage = shopDataPackage;
    }

    public String getHandMoney() {
        return handMoney;
    }

    public void setHandMoney(String handMoney) {
        this.handMoney = handMoney;
    }

    public String getMoneyRate() {
        return moneyRate;
    }

    public void setMoneyRate(String moneyRate) {
        this.moneyRate = moneyRate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getApplyRefundMoney() {
        return applyRefundMoney;
    }

    public void setApplyRefundMoney(String applyRefundMoney) {
        this.applyRefundMoney = applyRefundMoney;
    }

    public String getRateMsg() {
        return rateMsg;
    }

    public void setRateMsg(String rateMsg) {
        this.rateMsg = rateMsg;
    }

    @Override
    public String toString() {
        return "WxExcelObject{" +
                "tradeTime='" + tradeTime + '\'' +
                ", publicAccountId='" + publicAccountId + '\'' +
                ", shopNo='" + shopNo + '\'' +
                ", teYue='" + teYue + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", wxOrderNo='" + wxOrderNo + '\'' +
                ", shopOrderNo='" + shopOrderNo + '\'' +
                ", userSign='" + userSign + '\'' +
                ", tradeClass='" + tradeClass + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", bank='" + bank + '\'' +
                ", moneyClass='" + moneyClass + '\'' +
                ", shouldOrderNumber='" + shouldOrderNumber + '\'' +
                ", coupon='" + coupon + '\'' +
                ", wxRefundNo='" + wxRefundNo + '\'' +
                ", shopRefundNo='" + shopRefundNo + '\'' +
                ", refundMoney='" + refundMoney + '\'' +
                ", chongZhiRefund='" + chongZhiRefund + '\'' +
                ", refundClass='" + refundClass + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", shopDataPackage='" + shopDataPackage + '\'' +
                ", handMoney='" + handMoney + '\'' +
                ", moneyRate='" + moneyRate + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", applyRefundMoney='" + applyRefundMoney + '\'' +
                ", rateMsg='" + rateMsg + '\'' +
                '}';
    }
}
