package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.StockTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存种类
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface StockTypeDao extends BaseMapper<StockTypeEntity> {

}
