package com.yuand.smarteye.stock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 库存三级分区
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Data
@TableName("gms_ware_location")
public class WareLocationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分区id
     */
    @TableId
    private Long wlId;
    /**
     * 分区名称
     */
    private String name;
    /**
     * 父分区id
     */
    private Long parentWlid;
    /**
     * 层级
     */
    private Integer wlLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String goodsUnit;
    /**
     * 数量
     */
    private Integer goodsCount;

    /**
     * 子目录/子菜单，非数据库元素
     */
    @TableField(exist = false)  //表示非数据库表中元素
    @JsonInclude(JsonInclude.Include.NON_EMPTY)  //当值为空时，向前端发送的json数据不包括当前字段（过滤掉空值元素）
    private List<WareLocationEntity> children;

}
