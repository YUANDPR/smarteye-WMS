package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.OutbillEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出库单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-04 15:32:18
 */
@Mapper
public interface OutbillDao extends BaseMapper<OutbillEntity> {
    //今天新增出库单数量
    int queryTodayOutbill();

    //待处理的出库单数量
    int queryTodoOutbill();

}
