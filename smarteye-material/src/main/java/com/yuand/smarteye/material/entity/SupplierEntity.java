package com.yuand.smarteye.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 供用商
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("mms_supplier")
public class SupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 供用商id
     */
    @TableId
    private Long supplierId;
    /**
     * 供用商名
     */
    private String name;
    /**
     * 供用商logo地址
     */
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Integer showStatus;
    /**
     * 检索首字母
     */
    private String firstLetter;
    /**
     * 排序
     */
    private Integer sort;

}
