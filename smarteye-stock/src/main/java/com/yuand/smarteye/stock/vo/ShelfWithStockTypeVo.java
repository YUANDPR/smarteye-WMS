package com.yuand.smarteye.stock.vo;

import com.yuand.smarteye.stock.entity.ShelfEntity;
import com.yuand.smarteye.stock.entity.StockTypeEntity;
import lombok.Data;

import java.util.List;

@Data
public class ShelfWithStockTypeVo extends ShelfEntity {
    private Long wlId;

    private List<StockTypeEntity> stockTypeEntities;
}
