package com.yuand.smarteye.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.purchase.entity.BuyListEntity;

import java.util.Map;

/**
 * 采购单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-30 14:33:14
 */
public interface BuyListService extends IService<BuyListEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //查询未领取的采购单
    PageUtils queryPageUnreceive(Map<String, Object> params);

    //领取采购单status:2 或 分配采购单status:1  成功返回0，失败返回1
    int doList(BuyListEntity buyListEntity);
}

