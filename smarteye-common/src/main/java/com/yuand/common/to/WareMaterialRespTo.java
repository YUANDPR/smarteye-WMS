package com.yuand.common.to;

import lombok.Data;

import java.util.Date;

/**
 * 库存材料响应传输对象
 */
@Data
public class WareMaterialRespTo {

    /**
     * 分区名称
     */
    private String wlName;
    /**
     * 车间名称
     */
    private String wlpName;
    /**
     * 厂区名称
     */
    private String wlppName;

    /**
     * 库存品id
     */
    private Long warematerialId;
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
     * 真实库存
     */
    private Integer realCount;
    /**
     * 预警库存
     */
    private Integer warnCount;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 员工等级总体把握
     */
    private Integer levelNumb;
    /**
     * 车间id
     */
    private Long wlpId;
    /**
     * 厂区id
     */
    private Long wlppId;
}
