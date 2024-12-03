package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.StockEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 待上架或已上架的一批货物
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface StockDao extends BaseMapper<StockEntity> {
    //今天新增入库单数量
    int queryTodaybill();

    //待上架的入库单数量
    int queryTodobill();
}
