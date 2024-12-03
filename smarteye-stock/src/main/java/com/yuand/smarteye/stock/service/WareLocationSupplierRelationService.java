package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.SupplierEntity;
import com.yuand.smarteye.stock.entity.WareLocationSupplierRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 库存分区&供用商关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
public interface WareLocationSupplierRelationService extends IService<WareLocationSupplierRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //发来的请求只有id，没有name参数，我们联表查询出来补全name再保存
    void saveDetail(WareLocationSupplierRelationEntity wareLocationSupplierRelationEntity);

    //据一个分类id查询和他有关的所有供用商
    List<SupplierEntity> getSupplierByWlId(Long wlId);
}

