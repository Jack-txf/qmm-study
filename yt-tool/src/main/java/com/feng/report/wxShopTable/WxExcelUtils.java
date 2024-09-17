package com.feng.report.wxShopTable;

import com.alibaba.excel.EasyExcel;
import com.feng.report.wxShopTable.entity.WxRealObject;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/*
如果是csv格式，单元格里面都是String类型的，建议转成xlsx格式！！！！
如果是csv的文件，建议先把编码改为utf-8，将里面的单元格格式设置好。

wexin-0825-0913.xlsx
 */
public class WxExcelUtils {
    public static List<WxRealObject> readWxCsv( String excelPath ) {
        File file = new File(excelPath);
        WxReadListener wxReadListener = new WxReadListener();

        EasyExcel.read(file, WxRealObject.class, wxReadListener)
                .charset(StandardCharsets.UTF_8).sheet(0)
                .doRead();
        return wxReadListener.getDataList();
    }
}
