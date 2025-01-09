package com.feng.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String authorId;

    private String articleTitle;

    private String articleAbstract;

    private String articleContent;

    private Boolean isValid;
    private Date createTime;
    private Date modifyTime;
}
