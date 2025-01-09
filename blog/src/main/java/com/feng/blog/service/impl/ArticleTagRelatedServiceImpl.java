package com.feng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.blog.mapper.ArticleTagRelatedMapper;
import com.feng.blog.model.ArticleTagRelated;
import com.feng.blog.service.ArticleTagRelatedService;
import org.springframework.stereotype.Service;

/**
 * (ArticleTagRelated)表服务实现类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Service("articleTagRelatedService")
public class ArticleTagRelatedServiceImpl extends ServiceImpl<ArticleTagRelatedMapper, ArticleTagRelated> implements ArticleTagRelatedService {

}

