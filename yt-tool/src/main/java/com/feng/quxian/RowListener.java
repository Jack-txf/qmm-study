package com.feng.quxian;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.feng.quxian.pojo.ExcelRow;
import com.feng.quxian.really.NewExcelRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/24 15:04
 * @注释 行记录
 */
public class RowListener extends AnalysisEventListener<NewExcelRow> {
    // 用于存储读取到的数据
    private final List<NewExcelRow> excelDataList = new ArrayList<>();

    // 每读取到一行数据时调用此方法
    @Override
    public void invoke(NewExcelRow data, AnalysisContext analysisContext) {
        excelDataList.add(data);
    }

    // 读取完成后调用此方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("数据读取完成，共读取 " + excelDataList.size() + " 条记录。");
    }

    // 获取读取到的数据列表
    public List<NewExcelRow> getExcelRowList() {
        ArrayList<NewExcelRow> excelData = new ArrayList<>(excelDataList);
        excelDataList.clear();
        return excelData;
    }
}
