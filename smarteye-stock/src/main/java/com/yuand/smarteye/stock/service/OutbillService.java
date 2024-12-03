package com.yuand.smarteye.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.stock.entity.OutbillEntity;
import com.yuand.smarteye.stock.exception.LockStockException;
import com.yuand.smarteye.stock.exception.MiTokenException;
import com.yuand.smarteye.stock.vo.saveoutbillvo.OutbillVo;

import java.util.Map;

/**
 * 出库单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-04 15:32:18
 */
public interface OutbillService extends IService<OutbillEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存出库单及出库详情
     * 新建库存单的同时立即减掉对应库存，此时库存虽然没取走，但是后续出库单无法再出库这部分库存
     */
    void saveInfo(OutbillVo outbillVo) throws MiTokenException, LockStockException;

    //今天新增出库单数量
    int queryTodayOutbill();

    //待处理的出库单数量
    int queryTodoOutbill();

    /**
     * 查询outbillid的出库单信息
     */
    OutbillEntity getInfo(Long outbillId);
}

