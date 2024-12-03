package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.MaterialImagesDao;
import com.yuand.smarteye.material.entity.MaterialImagesEntity;
import com.yuand.smarteye.material.service.MaterialImagesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("materialImagesService")
public class MaterialImagesServiceImpl extends ServiceImpl<MaterialImagesDao, MaterialImagesEntity> implements MaterialImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialImagesEntity> page = this.page(
                new Query<MaterialImagesEntity>().getPage(params),
                new QueryWrapper<MaterialImagesEntity>()
        );

        return new PageUtils(page);
    }

}
