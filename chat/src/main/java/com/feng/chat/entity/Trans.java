package com.feng.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/29
 */
@Data
@TableName("trans_tab")
public class Trans {
    @TableId(type = IdType.AUTO)
    private Long transId;
    private String msg;
    private String describeMsg;
}
