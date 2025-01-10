package com.feng.blog.controller;

import com.feng.blog.common.R;
import com.feng.blog.dto.ArticleDto;
import com.feng.blog.model.Article;
import com.feng.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ArticleTab)表控制层
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@RestController
@RequestMapping("/articleTab")
@CrossOrigin
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("/addArticle")
    public R addArticle(@RequestBody ArticleDto articleDto) {
        log.info("【添加博文controller】参数: {}", articleDto);
        articleService.addBlog(articleDto);
        return R.success();
    }

    @GetMapping("/getArticle/{articleId}")
    public R getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleService.getArticleRandomOne(articleId);
        return R.success().setData("article", article);
    }

    @GetMapping("/hello")
    public R hello() {
        return R.success().setData("msg", "hello");
    }

}

