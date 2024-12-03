package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.StockImagesEntity;

import java.util.Map;

/**
 * 库存货物的图片集
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface StockImagesService extends IService<StockImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

