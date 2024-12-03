package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.WareLocationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存三级分区
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface WareLocationDao extends BaseMapper<WareLocationEntity> {

}
