package com.feng.report.wxShopTable;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.feng.report.wxShopTable.entity.WxRealObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WxReadListener extends AnalysisEventListener<WxRealObject> {
    private final List<WxRealObject> dataList = new ArrayList<>();
    @Override
    public void invoke(WxRealObject realObject, AnalysisContext analysisContext) {
        dataList.add(realObject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成！！！");
    }

}
