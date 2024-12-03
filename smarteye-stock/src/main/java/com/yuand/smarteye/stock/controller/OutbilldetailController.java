package com.yuand.smarteye.stock.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.OutbilldetailEntity;
import com.yuand.smarteye.stock.service.OutbilldetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 出库单详情
 */
@RestController
@RequestMapping("stock/outbilldetail")
public class OutbilldetailController {
    @Autowired
    private OutbilldetailService outbilldetailService;


    /**
     * 查询outbillid下的所有出库详情
     */
    @RequestMapping("{outbillId}/info")
    public R millinfo(@PathVariable("outbillId") Long outbillId) {
        List<OutbilldetailEntity> res = outbilldetailService.getByOutbillId(outbillId);

        return R.ok().put("data", res);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = outbilldetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{outbilldetailId}")
    public R info(@PathVariable("outbilldetailId") Long outbilldetailId) {
        OutbilldetailEntity outbilldetail = outbilldetailService.getById(outbilldetailId);

        return R.ok().put("outbilldetail", outbilldetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OutbilldetailEntity outbilldetail) {
        outbilldetailService.save(outbilldetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OutbilldetailEntity outbilldetail) {
        outbilldetailService.updateById(outbilldetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] outbilldetailIds) {
        outbilldetailService.removeByIds(Arrays.asList(outbilldetailIds));

        return R.ok();
    }

}
