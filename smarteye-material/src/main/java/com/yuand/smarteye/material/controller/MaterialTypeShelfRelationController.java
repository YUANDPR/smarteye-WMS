package com.yuand.smarteye.material.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.material.entity.MaterialTypeShelfRelationEntity;
import com.yuand.smarteye.material.service.MaterialTypeShelfRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 库存种类&货架关联
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@RestController
@RequestMapping("material/materialtypeshelfrelation")
public class MaterialTypeShelfRelationController {
    @Autowired
    private MaterialTypeShelfRelationService materialTypeShelfRelationService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = materialTypeShelfRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MaterialTypeShelfRelationEntity materialTypeShelfRelation = materialTypeShelfRelationService.getById(id);

        return R.ok().put("materialTypeShelfRelation", materialTypeShelfRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialTypeShelfRelationEntity materialTypeShelfRelation) {
        materialTypeShelfRelationService.save(materialTypeShelfRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialTypeShelfRelationEntity materialTypeShelfRelation) {
        materialTypeShelfRelationService.updateById(materialTypeShelfRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        materialTypeShelfRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
