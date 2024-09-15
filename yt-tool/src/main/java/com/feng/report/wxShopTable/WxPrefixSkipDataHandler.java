package com.feng.report.wxShopTable;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

// 微信商家导出来的文件中，每个单元格里面会有一个`前缀字符
public class WxPrefixSkipDataHandler implements Converter<String> {
    private static final String PREFIX = "`";
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String value = cellData.getStringValue();
        if (value.startsWith(PREFIX)) {
            return value.substring(1);
        }
        return value;
    }

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 在读取时不需要这个方法，这里可以不做处理或者抛出不支持操作的异常
        throw new UnsupportedOperationException("This method is not needed for reading.");
    }
}
