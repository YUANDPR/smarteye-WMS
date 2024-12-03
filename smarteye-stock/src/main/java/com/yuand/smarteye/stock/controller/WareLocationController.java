package com.yuand.smarteye.stock.controller;

import com.yuand.common.to.WareStockRespTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.WareLocationEntity;
import com.yuand.smarteye.stock.service.WareLocationService;
import com.yuand.smarteye.stock.vo.ListEchartsVo;
import com.yuand.smarteye.stock.vo.SonWareListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 库存三级分区
 */
@RestController
@RequestMapping("stock/warelocation")
public class WareLocationController {
    @Autowired
    private WareLocationService wareLocationService;

    /**
     * 根据父id查询，其所有子区
     */
    @RequestMapping("/list/son/{wlpId}")
    public R listson(@PathVariable("wlpId") Long wlpId) {
        List<SonWareListVo> res = wareLocationService.getSon(wlpId);
        return R.ok().put("data", res);

    }

    /**
     * 库存分区echarts
     */
    @RequestMapping("/list/echarts")
    public R listEcharts() {
        List<ListEchartsVo> entities = wareLocationService.listEcharts();
        return R.ok().put("data", entities);  //注意这里R的k对应前端取出的数据k
    }

    /**
     * 库存分区三级目录
     */
    @RequestMapping("/list/tree")
    public R listTree() {
        List<WareLocationEntity> entities = wareLocationService.listWithTree();
        return R.ok().put("data", entities);  //注意这里R的k对应前端取出的数据k
    }


    /**
     * 根据wlid、wlpid、wlppid查并设置对应名称
     */
    @RequestMapping("/transform")
    public R transform(@RequestBody List<WareStockRespTo> tos) {
        List<WareStockRespTo> tosRes = wareLocationService.transform(tos);

        return R.ok().put("data", tosRes);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareLocationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{wlId}")
    public R info(@PathVariable("wlId") Long wlId) {
        WareLocationEntity wareLocation = wareLocationService.getById(wlId);

        return R.ok().put("data", wareLocation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WareLocationEntity wareLocation) {
        wareLocationService.save(wareLocation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WareLocationEntity wareLocation) {
        wareLocationService.updateById(wareLocation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] wlIds) {
        wareLocationService.removeByIds(Arrays.asList(wlIds));

        return R.ok();
    }

}
