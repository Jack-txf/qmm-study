package com.feng.chat.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.feng.chat.common.R;
import com.feng.chat.entity.txf.SubjectCategory;
import com.feng.chat.mapper.txf.SubjectCategoryDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
@DS("txf")
public class DynamicDataController {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    @GetMapping("/get")
    public R getSubjectCategory() {
        SubjectCategory category = subjectCategoryDao.queryById(1L);
        return R.success().setData("category", category);
    }

}
