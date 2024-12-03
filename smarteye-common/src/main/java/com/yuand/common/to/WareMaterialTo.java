package com.yuand.common.to;

import lombok.Data;

/**
 * 库存材料传输对象
 */
@Data
public class WareMaterialTo {

    /**
     * 库存种类值
     */
    private String valueSelect;

    /**
     * 库存种类名称
     */
    private String materialTypeName;

    /**
     * 所属分区id
     */
    private Long wlId;

    /**
     * 货架名称
     */
    private String shelfName;

    /**
     * 当前库存数量
     */
    private Integer onematerialCount;

    /**
     * 车间id
     */
    private Long wlpId;

    /**
     * 厂区id
     */
    private Long wlppId;
}
