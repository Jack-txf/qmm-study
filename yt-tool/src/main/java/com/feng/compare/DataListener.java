package com.feng.compare;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.feng.compare.obj.ExcelData;

import java.util.ArrayList;
import java.util.List;

public class DataListener extends AnalysisEventListener<ExcelData> {
    // 用于存储读取到的数据
    private final List<ExcelData> excelDataList = new ArrayList<>();

    // 每读取到一行数据时调用此方法
    @Override
    public void invoke(ExcelData user, AnalysisContext analysisContext) {
        excelDataList.add(user);
    }

    // 读取完成后调用此方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("数据读取完成，共读取 " + excelDataList.size() + " 条记录。");
    }

    // 获取读取到的数据列表
    public List<ExcelData> getExcelDataList() {
        ArrayList<ExcelData> excelData = new ArrayList<>(excelDataList);
        excelDataList.clear();
        return excelData;
    }
}