package com.yuand.smarteye.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.stock.entity.ShelfEntity;
import com.yuand.smarteye.stock.vo.UpShelfInfoResp;
import com.yuand.smarteye.stock.vo.UpShelfInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 货架
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@Mapper
public interface ShelfDao extends BaseMapper<ShelfEntity> {
    //查询wlId下的货架的上架情况（onestock）
    //有货的货架
    List<UpShelfInfoResp> queryExpiredUpShelf(@Param("wlId") Long wlId);

    //查询wlId下的货架的上架情况（onestock）详情
    List<UpShelfInfoVo> queryUpshelfInfodetial(@Param("wlId") Long wlId, @Param("shelfName") String shelfName);
}
