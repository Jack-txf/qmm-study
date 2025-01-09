package com.feng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.blog.mapper.TagMapper;
import com.feng.blog.model.Tag;
import com.feng.blog.service.TagService;
import org.springframework.stereotype.Service;

/**
 * (TagTab)表服务实现类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

