package com.yuand.smarteye.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.stock.dao.StockValueDao;
import com.yuand.smarteye.stock.entity.StockValueEntity;
import com.yuand.smarteye.stock.service.StockValueService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("stockValueService")
public class StockValueServiceImpl extends ServiceImpl<StockValueDao, StockValueEntity> implements StockValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StockValueEntity> page = this.page(
                new Query<StockValueEntity>().getPage(params),
                new QueryWrapper<StockValueEntity>()
        );

        return new PageUtils(page);
    }

}
