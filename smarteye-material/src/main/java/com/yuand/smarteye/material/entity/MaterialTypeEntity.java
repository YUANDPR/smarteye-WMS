package com.yuand.smarteye.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 库存种类
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("mms_material_type")
public class MaterialTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 库存种类id
     */
    @TableId
    private Long materialTypeId;
    /**
     * 库存种类名
     */
    private String materialTypeName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 库存种类图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 库存种类类型[0-库存外属性，1-库存属性
     */
    private Integer materialTypeType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分区
     */
    private Long wlId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

}
