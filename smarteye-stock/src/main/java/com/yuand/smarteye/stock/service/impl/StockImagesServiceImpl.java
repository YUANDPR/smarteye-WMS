package com.yuand.smarteye.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.stock.dao.StockImagesDao;
import com.yuand.smarteye.stock.entity.StockImagesEntity;
import com.yuand.smarteye.stock.service.StockImagesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("stockImagesService")
public class StockImagesServiceImpl extends ServiceImpl<StockImagesDao, StockImagesEntity> implements StockImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StockImagesEntity> page = this.page(
                new Query<StockImagesEntity>().getPage(params),
                new QueryWrapper<StockImagesEntity>()
        );

        return new PageUtils(page);
    }

}
