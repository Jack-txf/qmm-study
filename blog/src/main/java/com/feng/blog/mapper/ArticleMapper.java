package com.feng.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * (ArticleTab)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}

