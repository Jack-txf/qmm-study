package com.feng.quxian;


import com.alibaba.excel.EasyExcel;
import com.feng.quxian.pojo.Category;
import com.feng.quxian.pojo.ExcelRow;
import com.feng.quxian.pojo.WritePojo;
import com.feng.quxian.really.NewExcelRow;
import com.feng.quxian.really.NewWritePojo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/24 15:02
 * @注释 main
 */
public class App {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RowListener rowListener = new RowListener();
        // 1.先读取到自营的数据
        String selfPath = "E:\\a_报表\\24年过完年的清算时刻\\区县店铺-自营-2024.xlsx";
        EasyExcel.read(new File(selfPath), NewExcelRow.class, rowListener)
                .sheet().doRead();
        List<NewExcelRow> selfDatas = rowListener.getExcelRowList();
        // 2.再读取到联营的数据
        String jointPath = "E:\\a_报表\\24年过完年的清算时刻\\区县店铺-联营-2024.xlsx";
        EasyExcel.read(new File(jointPath), NewExcelRow.class, rowListener)
                .sheet().doRead();
        List<NewExcelRow> jointDatas = rowListener.getExcelRowList();

        // 3.解析读取到的数据
        // key: 店铺名称 value:类别统计对象的map
        HashMap<String, HashMap<String, Category>> selfMap = new HashMap<>();
        // 3.1 解析自营的
        for ( NewExcelRow data : selfDatas ) {
            String storeName = data.getStoreName();
            if (!selfMap.containsKey(storeName)) { // 如果不包含
                selfMap.put(storeName, initSelf());
            }
            // key:类别 value:类别统计对象
            HashMap<String, Category> categoryMap = selfMap.get(storeName);
            // 算进去
            addSelfOrJoint(data, categoryMap, true);
            selfMap.put(storeName, categoryMap);
        }
        // 3.2 解析联营的
        HashMap<String, HashMap<String, Category>> jointMap = new HashMap<>();
        for ( NewExcelRow data : jointDatas ) {
            String storeName = data.getStoreName();
            if (!jointMap.containsKey(storeName)) { // 如果不包含
                jointMap.put(storeName, initJoint());
            }
            HashMap<String, Category> categoryMap = jointMap.get(storeName);
            addSelfOrJoint(data, categoryMap, false);
            jointMap.put(storeName, categoryMap);
        }
        // 4.写入结果excel
        List<NewWritePojo> res = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Category>> entry : selfMap.entrySet()) { // 遍历自营的
            String storeName = entry.getKey(); // 店铺名称
            HashMap<String, Category> categorym = entry.getValue();
            for (Map.Entry<String, Category> t : categorym.entrySet()) {
                String categoryName = t.getKey();
                res.add(new NewWritePojo(storeName, "自营" ,categoryName, t.getValue().getMaYang(), t.getValue().getShiYang()));
            }
        }
        for (Map.Entry<String, HashMap<String, Category>> entry : jointMap.entrySet()) { // 遍历联营的
            String storeName = entry.getKey();
            HashMap<String, Category> categorym = entry.getValue();
            for (Map.Entry<String, Category> t : categorym.entrySet()) {
                String categoryName = t.getKey();
                res.add(new NewWritePojo(storeName, "联营" ,categoryName, t.getValue().getMaYang(), t.getValue().getShiYang()));
            }
        }
        // 5.res需要排一个序, 按照店铺名称排序
        res.sort((o1, o2) -> {
            if (o1.getStoreName().equals(o2.getStoreName())) {
                if ( o1.getBigCategoryName().equals(o2.getBigCategoryName()))
                    return o1.getCategoryName().compareTo(o2.getCategoryName());
                return o1.getBigCategoryName().compareTo(o2.getBigCategoryName());
            }
            return o1.getStoreName().compareTo(o2.getStoreName());
        });
        // 6.写入excel
        String writePath = "E:\\a_报表\\24年过完年的清算时刻\\quxianQuery-new.xlsx";
        EasyExcel.write(new File(writePath)).sheet().doWrite(res);
        System.out.println("读取数据 到 写入 完成！耗时" + (System.currentTimeMillis() - start) / 1000 + "秒");

        // key店铺名称
        //HashMap<String, HashMap<String, Category>> all = new HashMap<>();
        //for (ExcelRow data : datas) {
        //    String storeName = data.getStoreName();
        //    if (!all.containsKey(storeName)) {
        //        all.put(storeName, init());
        //    }
        //    addCategory(data, all.get(storeName));
        //}
        //// 显示结果 -- 写入结果excel
        //List<WritePojo> w = new ArrayList<>();
        //for (Map.Entry<String, HashMap<String, Category>> entry : all.entrySet()) {
        //    //System.out.println("############" + entry.getKey() + "#############");
        //    //HashMap<String, Category> value = entry.getValue();
        //    //value.forEach( (k, v) -> System.out.println(k + ":" + v));
        //    HashMap<String, Category> value = entry.getValue();
        //    for ( Map.Entry<String, Category> t : value.entrySet() ) {
        //        WritePojo pojo = new WritePojo();
        //        pojo.setStoreName(entry.getKey()); // 店铺
        //        pojo.setCategoryName(t.getKey());
        //        pojo.setMaYang(t.getValue().getMaYang());
        //        pojo.setShiYang(t.getValue().getShiYang());
        //        w.add(pojo);
        //    }
        //}
        //String writePath = "E:\\a_报表\\24年过完年的清算时刻\\quxianQuery.xlsx";
        //EasyExcel.write(new File(writePath)).sheet().doWrite(w);
    }

    private static void addSelfOrJoint(NewExcelRow data, HashMap<String, Category> categoryMap, boolean sign) {
        String key;
        if( sign ) { // 当做自营的加进去
            key = "一般图书";
            if ( data.getBook_class_id().equals("0808000000")) key = "教材";
            else if ( data.getBook_type().equals("A2") ) {
                if ( data.getBook_class_id().equals("1008000000")) key = "进口音像";
                else key = "音像";
            }
        } else { // 当做联营的加进去
            key = "其他";
            if ( data.getBook_class_id().equals("3004000000")) key = "小天才手表";
            else if ( data.getBook_class_id().equals("3503000000")) key = "手机";
        }
        Category category = categoryMap.get(key);
        category.addMaYang(data.getOriginal_price() * data.getProduct_quantity());
        category.addShiYang(data.getSell_price() * data.getProduct_quantity());
        categoryMap.put(key, category);
    }

    public static HashMap<String, Category> initSelf() {
        HashMap<String, Category> res = new HashMap<>();
        res.put("一般图书", new Category("一般图书", 0, 0.0, 0.0));
        res.put("教材", new Category("教材", 0, 0.0, 0.0));
        res.put("音像", new Category("音像", 0, 0.0, 0.0));
        res.put("进口音像", new Category("进口音像", 0, 0.0, 0.0));
        return res;
    }
    public static HashMap<String, Category> initJoint() {
        HashMap<String, Category> res = new HashMap<>();
        res.put("手机", new Category("手机", 0, 0.0, 0.0));
        res.put("小天才手表", new Category("小天才手表", 0, 0.0, 0.0));
        res.put("其他", new Category("其他", 0, 0.0, 0.0));
        return res;
    }

    public static void addCategory( ExcelRow rowData, HashMap<String, Category> tmp ) {
        String className = rowData.getName(); // 类别
        if ( className == null || className.isEmpty() ) {
            className = "联营";
        }
        else if ( className.contains("教材") ) {
            className = "教材";
        } else if ( className.contains("音") || className.contains("CD") ) {
            className = "音像";
        } else {
            className = "一般图书";
        }
        Category category = tmp.get(className);
        category.addMaYang(rowData.getOriginal_price());
        category.addShiYang(rowData.getSell_price());
        tmp.put(className, category);
    }
}
