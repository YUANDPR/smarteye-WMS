package com.yuand.smarteye.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.material.entity.MaterialImagesEntity;

import java.util.Map;

/**
 * 库存货物的图片集
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface MaterialImagesService extends IService<MaterialImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

