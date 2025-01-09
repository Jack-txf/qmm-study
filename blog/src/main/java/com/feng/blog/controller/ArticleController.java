package com.feng.blog.controller;

import com.feng.blog.common.R;
import com.feng.blog.dto.ArticleDto;
import com.feng.blog.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (ArticleTab)表控制层
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@RestController
@RequestMapping("/articleTab")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("/addArticle")
    public R addArticle(@RequestBody ArticleDto articleDto) {
        return R.success();
    }

}

