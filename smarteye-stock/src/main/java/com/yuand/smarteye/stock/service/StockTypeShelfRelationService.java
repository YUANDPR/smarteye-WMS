package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.StockTypeShelfRelationEntity;
import com.yuand.smarteye.stock.vo.ShelfRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 库存种类&货架关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface StockTypeShelfRelationService extends IService<StockTypeShelfRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //添加库存种类与货架关联关系（这是个重载方法）
    void saveBatch(List<ShelfRelationVo> vos);

}

