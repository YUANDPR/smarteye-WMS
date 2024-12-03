package com.yuand.smarteye.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.stock.dao.OutbilldetailDao;
import com.yuand.smarteye.stock.entity.OutbilldetailEntity;
import com.yuand.smarteye.stock.service.OutbilldetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("outbilldetailService")
public class OutbilldetailServiceImpl extends ServiceImpl<OutbilldetailDao, OutbilldetailEntity> implements OutbilldetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OutbilldetailEntity> page = this.page(
                new Query<OutbilldetailEntity>().getPage(params),
                new QueryWrapper<OutbilldetailEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询outbillid下的所有出库详情
     */
    @Override
    public List<OutbilldetailEntity> getByOutbillId(Long outbillId) {
        QueryWrapper<OutbilldetailEntity> queryWrapper = new QueryWrapper<OutbilldetailEntity>().eq("outbill_id", outbillId);
        List<OutbilldetailEntity> outbilldetailEntities = this.baseMapper.selectList(queryWrapper);
        return outbilldetailEntities;
    }


}
