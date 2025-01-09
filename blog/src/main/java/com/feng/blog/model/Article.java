package com.feng.blog.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * (ArticleTab)表实体类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("article_tab")
public class Article implements Serializable{
    private static final long serialVersionUID = 1L;

    //博客文章ID
    @TableId(type = IdType.AUTO)
    private Long articleId;

    //作者ID，也就是用户ID
    private String authorId;
    //文章标题
    private String articleTitle;
    //摘要
    private String articleAbstract;
    //文章内容 [ 存储的是markdown格式的 ]
    private String articleContent;
    //该记录是否有效
    private Boolean isValid;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}

