package com.yuand.common.to;

import lombok.Data;

/**
 * 目录资源传输对象
 */
@Data
public class CatalogueResourceTo {
    /**
     * 目录名称
     */
    private String name;
    /**
     * 目录ID
     */
    private Long id;
    /**
     * 上级目录的资源传输对象
     */
    private CatalogueResourceTo catalogueResourceTo;
}
