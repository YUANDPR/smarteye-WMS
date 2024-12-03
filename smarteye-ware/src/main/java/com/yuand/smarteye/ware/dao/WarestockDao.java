package com.yuand.smarteye.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.ware.entity.WarestockEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存品
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-29 14:13:03
 */
@Mapper
public interface WarestockDao extends BaseMapper<WarestockEntity> {
    //返回低于预警库存的货物id、valueSelect
    List<WarestockEntity> getLowWarnCount();

    //返回低于预警库存的货物
    List<WarestockEntity> getDangerCount(@Param("curPage") long curPage, @Param("limit") long limit);
}
