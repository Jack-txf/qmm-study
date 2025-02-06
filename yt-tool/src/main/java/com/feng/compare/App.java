package com.feng.compare;

import com.alibaba.excel.EasyExcel;
import com.feng.compare.obj.ExcelData;

import java.io.File;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //String path = "E:\\a_报表\\销售对比\\一个月的对比\\24春节-腊月二十-正月初六6销售.xlsx";
        String path = "E:\\a_报表\\销售对比\\一个月的对比\\25春节-腊月二十-正月初八8销售.xlsx";
        DataListener listener = new DataListener();
        // 调用 EasyExcel 的 read 方法进行读取
        EasyExcel.read(new File(path), ExcelData.class, listener).sheet().doRead();
        // 获取读取到的数据
        List<ExcelData> dataList = listener.getExcelDataList();
        // 解析一下数据
        Reslover.slove(dataList);
    }
}
