package com.feng.blog.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * (TagTab)表实体类
 *
 * @author makejava
 * @since 2025-01-06 19:25:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tag_tab")
public class Tag implements Serializable{
    private static final long serialVersionUID = -90602462624697302L;
    //文章标签ID
    @TableId(type = IdType.AUTO)
    private Long articleTagId;
    //标签名字
    private String tagName;

    private Boolean isValid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}

