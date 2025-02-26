package com.yuand.smarteye.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.material.entity.MbillEntity;
import com.yuand.smarteye.material.vo.MouthMbillRespVo;
import com.yuand.smarteye.material.vo.MouthMbillbingtuVo;
import com.yuand.smarteye.material.vo.savembillvo.MbillVo;

import java.util.List;
import java.util.Map;

/**
 * 物料申请单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-01 19:48:53
 */
public interface MbillService extends IService<MbillEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存申请表、申请表详情
     */
    void saveInfo(MbillVo mbillVo);

    /**
     * 撤回申请
     */
    int revoke(Long mbillId);

    /**
     * 审批物料申请
     */
    int handle(Long mbillId, int status);

    /**
     * 查询今天新增物料申请单数量
     */
    int queryTodayMbill();

    /**
     * 待处理的物料申请单数量
     */
    int queryTodoMbill();

    /**
     * 查询近一个月物料申请单的数量趋势，以天为分组
     */
    List<MouthMbillRespVo> queryMouthMbill();

    //查询近一个月物料申请单来自的分库的分布（饼图）
    List<MouthMbillbingtuVo> queryMouthMbillbingtu();
}

