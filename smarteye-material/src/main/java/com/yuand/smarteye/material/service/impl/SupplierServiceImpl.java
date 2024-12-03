package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.SupplierDao;
import com.yuand.smarteye.material.entity.SupplierEntity;
import com.yuand.smarteye.material.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, SupplierEntity> implements SupplierService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SupplierEntity> page = this.page(
                new Query<SupplierEntity>().getPage(params),
                new QueryWrapper<SupplierEntity>()
        );

        return new PageUtils(page);
    }

}
