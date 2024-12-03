package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.AskmbillDao;
import com.yuand.smarteye.material.entity.AskmbillEntity;
import com.yuand.smarteye.material.service.AskmbillService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("askmbillService")
public class AskmbillServiceImpl extends ServiceImpl<AskmbillDao, AskmbillEntity> implements AskmbillService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AskmbillEntity> page = this.page(
                new Query<AskmbillEntity>().getPage(params),
                new QueryWrapper<AskmbillEntity>()
        );

        return new PageUtils(page);
    }

}
