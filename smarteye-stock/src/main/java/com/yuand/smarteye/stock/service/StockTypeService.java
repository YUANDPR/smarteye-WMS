package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.StockTypeEntity;
import com.yuand.smarteye.stock.vo.ShelfRelationVo;
import com.yuand.smarteye.stock.vo.StockTypeRespVo;
import com.yuand.smarteye.stock.vo.StockTypeShelfVo;

import java.util.List;
import java.util.Map;

/**
 * 库存种类
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface StockTypeService extends IService<StockTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //查询某三级目录分类目录下的库存种类，0为全部
    PageUtils queryBaseStockTypePage(Map<String, Object> params, Long wlId, String type);

    //保存库存种类信息 及 对应的库存与货架关系
    void saveDetial(StockTypeShelfVo stockTypeShelfVo);

    //根据id查询，回显
    StockTypeRespVo getStockTypeInfo(Long stockTypeId);

    //库存种类信息 及 对应的库存与货架关系
    void updateDetial(StockTypeShelfVo stockTypeShelfVo);

    //获取货架关联的所有库存种类
    List<StockTypeEntity> getRelationStockType(Long shelfId);

    //获取货架未关联的所有库存种类
    PageUtils getNoRelationStockType(Map<String, Object> params, Long shelfId);

    //删除库存种类与货架的关联关系
    void deleteRelation(ShelfRelationVo[] vos);

}

