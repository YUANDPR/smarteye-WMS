package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.StockTypeShelfRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存种类&货架关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface StockTypeShelfRelationDao extends BaseMapper<StockTypeShelfRelationEntity> {
    //删除relationEntity集合
    void deleteBatchRelation(@Param("entities") List<StockTypeShelfRelationEntity> entities);
}
