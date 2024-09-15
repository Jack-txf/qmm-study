package com.feng.report.wxShopTable;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.feng.report.wxShopTable.convertor.ExcelConvertor;
import com.feng.report.wxShopTable.entity.RealObject;
import com.feng.report.wxShopTable.entity.WxExcelObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WxExcelUtils {
    public static List<RealObject> readWxCsv( String excelPath ) {
        File file = new File(excelPath);

        ExcelReaderBuilder reader = EasyExcel.read(file).charset(StandardCharsets.UTF_8);
        ExcelReaderSheetBuilder sheetBuilder = reader.sheet(0);
        sheetBuilder.registerConverter(new WxPrefixSkipDataHandler()); // 每个字段设置convert

        List<WxExcelObject> data = sheetBuilder.doReadSync(); // 读取出来list，里面每个字段都是String类型的
        System.out.println(data.size());
        // System.out.println(data.getClass());
        data.forEach(System.out::println);

        List<RealObject> realObjects = new ArrayList<>();

        // data.forEach( item -> realObjects.add(ExcelConvertor.INSTANCE.WxExcelObjectToRealObject(item)));
        return null;

    }
}
