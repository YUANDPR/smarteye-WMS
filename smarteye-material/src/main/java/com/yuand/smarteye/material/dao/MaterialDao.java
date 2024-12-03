package com.yuand.smarteye.material.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.material.entity.MaterialEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 待上架或已上架的一批货物
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface MaterialDao extends BaseMapper<MaterialEntity> {

}
