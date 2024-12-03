package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.StockValueEntity;

import java.util.Map;

/**
 * 货物的库存种类值
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface StockValueService extends IService<StockValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

