package com.yuand.smarteye.material.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.material.entity.OnematerialEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 待上架或已上架的一种货物
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-26 22:18:23
 */
@Mapper
public interface OnematerialDao extends BaseMapper<OnematerialEntity> {

}
