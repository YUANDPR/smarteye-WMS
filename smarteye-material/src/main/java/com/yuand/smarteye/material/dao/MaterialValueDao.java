package com.yuand.smarteye.material.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.material.entity.MaterialValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货物的库存种类值
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface MaterialValueDao extends BaseMapper<MaterialValueEntity> {

}
