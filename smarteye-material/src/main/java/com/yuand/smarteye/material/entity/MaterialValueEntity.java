package com.yuand.smarteye.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 货物的库存种类值
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("mms_material_value")
public class MaterialValueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 货物id
     */
    private Long materialId;
    /**
     * 库存种类id
     */
    private Long materialTypeId;
    /**
     * 库存种类名
     */
    private String materialTypeName;
    /**
     * 库存种类值
     */
    private String materialTypeValue;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 顺序
     */
    private Integer materialTypeSort;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    private Integer quickShow;

}
