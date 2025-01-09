package com.feng.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
/**
 * (ArticleImgRelated)表实体类
 *
 * @author makejava
 * @since 2025-01-06 19:25:36
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("article_img_related")
public class ArticleImgRelated implements Serializable {
    private static final long serialVersionUID = -40326385982845903L;
    //文章-图片关联ID
    @TableId(type = IdType.AUTO)
    private Long articleImgRelatedId;
    //文章ID
    private Long articleId;
    //图片ID
    private Long imgId;

    private Boolean isValid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}

