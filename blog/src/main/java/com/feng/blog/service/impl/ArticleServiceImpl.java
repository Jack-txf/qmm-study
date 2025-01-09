package com.feng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.blog.mapper.ArticleMapper;
import com.feng.blog.model.Article;
import com.feng.blog.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * (ArticleTab)表服务实现类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}

