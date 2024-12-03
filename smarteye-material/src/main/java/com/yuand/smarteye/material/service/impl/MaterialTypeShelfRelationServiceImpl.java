package com.yuand.smarteye.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.smarteye.material.dao.MaterialTypeShelfRelationDao;
import com.yuand.smarteye.material.dao.ShelfDao;
import com.yuand.smarteye.material.entity.MaterialTypeShelfRelationEntity;
import com.yuand.smarteye.material.service.MaterialTypeShelfRelationService;
import com.yuand.smarteye.material.vo.ShelfRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("materialTypeShelfRelationService")
public class MaterialTypeShelfRelationServiceImpl extends ServiceImpl<MaterialTypeShelfRelationDao, MaterialTypeShelfRelationEntity> implements MaterialTypeShelfRelationService {

    @Autowired
    ShelfDao shelfDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialTypeShelfRelationEntity> page = this.page(
                new Query<MaterialTypeShelfRelationEntity>().getPage(params),
                new QueryWrapper<MaterialTypeShelfRelationEntity>()
        );

        return new PageUtils(page);
    }

    //添加库存种类与货架关联关系
    @Override
    public void saveBatch(List<ShelfRelationVo> vos) {
        //接受到vos后把其转为AttrAttrgroupRelationEntity
        //再调用批量保存方法进行保存
        List<MaterialTypeShelfRelationEntity> collect = vos.stream().map(item -> {
            MaterialTypeShelfRelationEntity relationEntity = new MaterialTypeShelfRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }


}
