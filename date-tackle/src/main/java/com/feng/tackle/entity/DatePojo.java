package com.feng.tackle.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * (DateTab)表实体类
 *
 * @author makejava
 * @since 2024-07-29 20:03:47
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("date_tab")
public class DatePojo {
    @TableId(type = IdType.AUTO)
    private Integer tId;

    private String msg;

    // idea标记代码：让光标处在一行，按F11键；   Shift+F11键显示所有标签的代码
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai") // 与数据库所处的时区要一致 ！！
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date modifyTime;
}

