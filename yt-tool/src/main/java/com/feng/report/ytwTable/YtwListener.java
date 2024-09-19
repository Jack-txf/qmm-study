package com.feng.report.ytwTable;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.feng.report.ytwTable.eneity.YtwExcelData;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class YtwListener extends AnalysisEventListener<YtwExcelData> {
    private List<YtwExcelData> data = new ArrayList<>();
    @Override
    public void invoke(YtwExcelData ytwExcelData, AnalysisContext analysisContext) {
        data.add(ytwExcelData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("文件读取完成");
    }
}
