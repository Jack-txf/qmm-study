package com.feng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.blog.dto.ArticleDto;
import com.feng.blog.mapper.ArticleMapper;
import com.feng.blog.model.Article;
import com.feng.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (ArticleTab)表服务实现类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void addBlog(ArticleDto articleDto) {
        Date date = new Date();
        // 1.将dto转为实体
        Article article = new Article(null,
                "1315646asd",
                "SpringBoot原理分析",
                "aaaaaaaaaaaaaaaaaa哈哈哈就是测试文章的",
                articleDto.getArticleContent(),
                true,
                date,
                date
        );
        articleMapper.insert(article);
    }

    @Override
    public Article getArticleRandomOne(Long articleId) {
        return articleMapper.selectById(articleId);
    }
}

