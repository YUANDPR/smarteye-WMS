package com.yuand.smarteye.stock.vo;

import com.yuand.smarteye.stock.entity.StockTypeEntity;
import lombok.Data;

@Data
public class StockTypeShelfVo extends StockTypeEntity {
    private Long shelfId;
}
