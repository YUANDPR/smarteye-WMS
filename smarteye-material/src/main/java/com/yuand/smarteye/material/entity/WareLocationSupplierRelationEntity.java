package com.yuand.smarteye.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 库存分区&供用商关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("mms_ware_location_supplier_relation")
public class WareLocationSupplierRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 货架id
     */
    private Long supplierId;
    /**
     * 分区id
     */
    private Long wlId;
    /**
     *
     */
    private String supplierName;
    /**
     *
     */
    private String wlName;

}
