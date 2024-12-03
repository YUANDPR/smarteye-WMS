package com.yuand.smarteye.ware.controller;

import com.yuand.common.to.WareMaterialTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.ware.entity.WarematerialEntity;
import com.yuand.smarteye.ware.service.WarematerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 库存品
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-05 17:35:57
 */
@RestController
@RequestMapping("ware/warematerial")
public class WarematerialController {
    @Autowired
    private WarematerialService warematerialService;

    /**
     * 向wms中添加warematerial库存(分库库存)
     */
    @RequestMapping("/add")
    public R add(@RequestBody WareMaterialTo wareMaterialTo) {
        warematerialService.addMaterial(wareMaterialTo);
        return R.ok();
    }

    /**
     * 列表 条件查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = warematerialService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{warematerialId}")
    public R info(@PathVariable("warematerialId") Long warematerialId) {
        WarematerialEntity warematerial = warematerialService.getById(warematerialId);

        return R.ok().put("data", warematerial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WarematerialEntity warematerial) {
        warematerialService.save(warematerial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WarematerialEntity warematerial) {
        warematerialService.updateById(warematerial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] warematerialIds) {
        warematerialService.removeByIds(Arrays.asList(warematerialIds));

        return R.ok();
    }

}
