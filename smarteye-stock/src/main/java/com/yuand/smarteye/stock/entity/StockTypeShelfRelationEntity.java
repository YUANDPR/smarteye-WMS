package com.yuand.smarteye.stock.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 库存种类&货架关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("gms_stock_type_shelf_relation")
public class StockTypeShelfRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 库存种类id
     */
    private Long stockTypeId;
    /**
     * 货架id
     */
    private Long shelfId;
    /**
     * 库存种类组内排序
     */
    private Integer stockTypeSort;

}
