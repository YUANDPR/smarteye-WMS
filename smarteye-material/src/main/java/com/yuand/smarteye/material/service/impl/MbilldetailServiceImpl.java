package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.constant.MaterialConstant;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.MbilldetailDao;
import com.yuand.smarteye.material.entity.MbilldetailEntity;
import com.yuand.smarteye.material.service.MbilldetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("mbilldetailService")
public class MbilldetailServiceImpl extends ServiceImpl<MbilldetailDao, MbilldetailEntity> implements MbilldetailService {

    @Autowired
    MbilldetailService mbilldetailService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MbilldetailEntity> page = this.page(
                new Query<MbilldetailEntity>().getPage(params),
                new QueryWrapper<MbilldetailEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询mbillid下的所有申请详情
     */
    @Override
    public List<MbilldetailEntity> getByMbillId(Long mbillId) {
        QueryWrapper<MbilldetailEntity> queryWrapper = new QueryWrapper<MbilldetailEntity>().eq("mbill_id", mbillId);
        List<MbilldetailEntity> mbilldetailEntities = this.baseMapper.selectList(queryWrapper);
        return mbilldetailEntities;
    }

    /**
     * 物料申请确认送达
     */
    @Override
    public void received(Long mbilldetailId) {
        //先判断是否是申请状态，只有申请状态的才可以改为送达状态
        QueryWrapper<MbilldetailEntity> queryWrapper = new QueryWrapper<MbilldetailEntity>().eq("mbilldetail_id", mbilldetailId);
        MbilldetailEntity entity = mbilldetailService.getOne(queryWrapper);
        if (entity.getStatus() == MaterialConstant.MaterialRequestDetailStatusEnum.APPLYING.getCode()) {
            entity.setStatus(MaterialConstant.MaterialRequestDetailStatusEnum.RECEIVED.getCode());
        }
        this.baseMapper.updateById(entity);
    }

}
