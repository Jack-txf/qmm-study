// package com.feng.report.wxShopTable.convertor;
//
// import com.feng.report.wxShopTable.entity.RealObject;
// import com.feng.report.wxShopTable.entity.WxExcelObject;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.Named;
// import org.mapstruct.factory.Mappers;
//
// import java.text.SimpleDateFormat;
// import java.util.Date;
//
// @Mapper
// public interface ExcelConvertor {
//     ExcelConvertor INSTANCE = Mappers.getMapper(ExcelConvertor.class);
//     // List<RealObject> ListWxExcelObjectToRealObject(List<WxExcelObject> wxExcelObject);
//
//     @Mapping(target = "tradeTime", source = "tradeTime", qualifiedByName = "stringToDate")
//     @Mapping(target = "shouldOrderNumber", source = "shouldOrderNumber", qualifiedByName = "stringToDouble")
//     @Mapping(target = "refundMoney", source = "refundMoney", qualifiedByName = "stringToDouble")
//     RealObject WxExcelObjectToRealObject(WxExcelObject wxExcelObject);
//
//     @Named("stringToDate")
//     default Date stringToDate(String tradeTime) {
//         if (tradeTime == null) {
//             return null;
//         }
//         try {
//             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             return sdf.parse(tradeTime);
//         } catch (Exception e) {
//             // 根据实际情况处理解析异常
//             return null;
//         }
//     }
//
//     @Named("stringToDouble")
//     default Double stringToDouble(String value) {
//         if (value == null) {
//             return null;
//         }
//         try {
//             return Double.parseDouble(value);
//         } catch (NumberFormatException e) {
//             // 根据实际情况处理解析异常
//             return null;
//         }
//     }
// }
