package com.feng.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * (ArticleTagRelated)表实体类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("article_tag_related")
public class ArticleTagRelated implements Serializable{
    private static final long serialVersionUID = 1L;
    //关系ID
    @TableId(type = IdType.AUTO)
    private Long articleTagRelatedId;
    //文章ID
    private Long articleId;
    //标签ID
    private Long tagId;

    private Boolean isValid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}

