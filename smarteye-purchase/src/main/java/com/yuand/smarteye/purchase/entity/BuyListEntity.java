package com.yuand.smarteye.purchase.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-30 14:33:14
 */
@Data
@TableName("pms_buy_list")
public class BuyListEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long buyListId;
    /**
     *
     */
    private Long assigneeId;
    /**
     *
     */
    private String assigneeName;
    /**
     *
     */
    private String phone;
    /**
     *
     */
    private Integer priority;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     *
     */
    private String buyListName;

}
