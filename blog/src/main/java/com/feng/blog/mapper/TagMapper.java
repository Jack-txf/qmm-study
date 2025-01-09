package com.feng.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TagTab)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}

