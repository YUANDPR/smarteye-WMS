package com.yuand.common.to;

import lombok.Data;

/**
 * 库存预警计数传输对象
 */
@Data
public class LowWarnCountTo {

    /**
     * 仓库库存ID
     */
    private Long warestockId;
    /**
     * 预警值选择
     * 描述选用的预警值配置
     */
    private String valueSelect;
    /**
     * 所属分区id
     * 表示物品所在的仓库分区
     */
    private Long wlId;
    /**
     * 货架名称
     * 物品存放的具体货架名称
     */
    private String shelfName;
}
