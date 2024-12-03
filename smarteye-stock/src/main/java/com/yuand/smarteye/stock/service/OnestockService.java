package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.OnestockEntity;

import java.util.List;
import java.util.Map;

/**
 * 待上架或已上架的一种货物
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-26 22:18:23
 */
public interface OnestockService extends IService<OnestockEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //检索查询
    PageUtils queryPageByCondition(Map<String, Object> params);

    //上架onestock，并向wms中添加warestock库存
    void up(Long onestockId);

    /**
     * 查询stockid下的所有详情
     */
    List<OnestockEntity> getByStockId(Long stockId);
}

