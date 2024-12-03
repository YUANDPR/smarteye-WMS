package com.yuand.smarteye.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.purchase.entity.BuyDemandEntity;
import com.yuand.smarteye.purchase.vo.MergeVo;
import com.yuand.smarteye.purchase.vo.WarnEntityInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 采购需求
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-30 14:33:14
 */
public interface BuyDemandService extends IService<BuyDemandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //合并采购需求（就是添加到采购单即添加采购需求表中的采购单id）
    List<Long> mergePurchase(MergeVo mergeVo);

    //自动检测预警库存，生成采购需求
    List<WarnEntityInfoVo> autoRemand();
}

