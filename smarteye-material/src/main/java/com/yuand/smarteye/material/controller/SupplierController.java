package com.yuand.smarteye.material.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.material.entity.SupplierEntity;
import com.yuand.smarteye.material.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 供用商
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-24 14:00:06
 */
@RestController
@RequestMapping("material/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    /**
     * 修改状态
     */
    @RequestMapping("/update/status")
    public R updateStatus(@RequestBody SupplierEntity supplierEntity) {
        supplierService.updateById(supplierEntity);

        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = supplierService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{supplierId}")
    public R info(@PathVariable("supplierId") Long supplierId) {
        SupplierEntity supplier = supplierService.getById(supplierId);

        return R.ok().put("supplier", supplier);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SupplierEntity supplier) {
        supplierService.save(supplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SupplierEntity supplier) {
        supplierService.updateById(supplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] supplierIds) {
        supplierService.removeByIds(Arrays.asList(supplierIds));

        return R.ok();
    }

}
