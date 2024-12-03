package com.yuand.smarteye.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.material.entity.MbilldetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料申请单详情
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-01 19:48:53
 */
public interface MbilldetailService extends IService<MbilldetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询mbillid下的所有申请详情
     */
    List<MbilldetailEntity> getByMbillId(Long mbillId);

    /**
     * 物料申请确认送达
     */
    void received(Long mbilldetailId);
}

