package com.feng.quxian;


import com.alibaba.excel.EasyExcel;
import com.feng.quxian.pojo.Category;
import com.feng.quxian.pojo.WritePojo;

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
        String path = "E:\\a_报表\\24年过完年的清算时刻\\quxian.xlsx";
        RowListener rowListener = new RowListener();
        EasyExcel.read(new File(path), ExcelRow.class, rowListener)
                .sheet().doRead();

        // 获取读取到的数据
        List<ExcelRow> datas = rowListener.getExcelRowList();
        // key店铺名称
        HashMap<String, HashMap<String, Category>> all = new HashMap<>();
        for (ExcelRow data : datas) {
            String storeName = data.getStoreName();
            if (!all.containsKey(storeName)) {
                all.put(storeName, init());
            }
            addCategory(data, all.get(storeName));
        }
        // 显示结果 -- 写入结果excel
        List<WritePojo> w = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Category>> entry : all.entrySet()) {
            //System.out.println("############" + entry.getKey() + "#############");
            //HashMap<String, Category> value = entry.getValue();
            //value.forEach( (k, v) -> System.out.println(k + ":" + v));
            HashMap<String, Category> value = entry.getValue();
            for ( Map.Entry<String, Category> t : value.entrySet() ) {
                WritePojo pojo = new WritePojo();
                pojo.setStoreName(entry.getKey()); // 店铺
                pojo.setCategoryName(t.getKey());
                pojo.setMaYang(t.getValue().getMaYang());
                pojo.setShiYang(t.getValue().getShiYang());
                w.add(pojo);
            }
        }
        String writePath = "E:\\a_报表\\24年过完年的清算时刻\\quxianQuery.xlsx";
        EasyExcel.write(new File(writePath)).sheet().doWrite(w);
    }

    public static HashMap<String, Category> init() {
        HashMap<String, Category> res = new HashMap<>();
        res.put("一般图书", new Category("一般图书", 0, 0.0, 0.0));
        res.put("音像", new Category("音像", 0, 0.0, 0.0));
        res.put("教材", new Category("教材", 0, 0.0, 0.0));
        res.put("联营", new Category("联营", 0, 0.0, 0.0));
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
