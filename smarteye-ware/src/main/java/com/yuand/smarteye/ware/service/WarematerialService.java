package com.yuand.smarteye.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.to.WareMaterialTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.ware.entity.WarematerialEntity;

import java.util.Map;

/**
 * 库存品
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-05 17:35:57
 */
public interface WarematerialService extends IService<WarematerialEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 向wms中添加warematerial库存(分库库存)
     */
    void addMaterial(WareMaterialTo wareMaterialTo);

    //条件查询
    PageUtils queryPageByCondition(Map<String, Object> params);
}

