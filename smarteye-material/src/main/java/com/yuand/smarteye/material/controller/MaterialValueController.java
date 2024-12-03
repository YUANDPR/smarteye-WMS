package com.yuand.smarteye.material.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.material.entity.MaterialValueEntity;
import com.yuand.smarteye.material.service.MaterialValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 货物的库存种类值
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@RestController
@RequestMapping("material/materialvalue")
public class MaterialValueController {
    @Autowired
    private MaterialValueService materialValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = materialValueService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MaterialValueEntity materialValue = materialValueService.getById(id);

        return R.ok().put("materialValue", materialValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialValueEntity materialValue) {
        materialValueService.save(materialValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialValueEntity materialValue) {
        materialValueService.updateById(materialValue);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        materialValueService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
