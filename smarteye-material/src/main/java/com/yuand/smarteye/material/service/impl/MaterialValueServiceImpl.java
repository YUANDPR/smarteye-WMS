package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.MaterialValueDao;
import com.yuand.smarteye.material.entity.MaterialValueEntity;
import com.yuand.smarteye.material.service.MaterialValueService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("materialValueService")
public class MaterialValueServiceImpl extends ServiceImpl<MaterialValueDao, MaterialValueEntity> implements MaterialValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialValueEntity> page = this.page(
                new Query<MaterialValueEntity>().getPage(params),
                new QueryWrapper<MaterialValueEntity>()
        );

        return new PageUtils(page);
    }

}
