package com.yuand.smarteye.material.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.material.entity.MaterialTypeShelfRelationEntity;
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
public interface MaterialTypeShelfRelationDao extends BaseMapper<MaterialTypeShelfRelationEntity> {
    //删除relationEntity集合
    void deleteBatchRelation(@Param("entities") List<MaterialTypeShelfRelationEntity> entities);
}
