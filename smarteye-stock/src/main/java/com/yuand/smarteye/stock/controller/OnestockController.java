package com.yuand.smarteye.stock.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuand.common.exception.BizCodeEnum;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.OnestockEntity;
import com.yuand.smarteye.stock.entity.ShelfEntity;
import com.yuand.smarteye.stock.entity.StockTypeShelfRelationEntity;
import com.yuand.smarteye.stock.service.OnestockService;
import com.yuand.smarteye.stock.service.ShelfService;
import com.yuand.smarteye.stock.service.StockTypeShelfRelationService;
import com.yuand.smarteye.stock.vo.OneStockRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 待上架或已上架的一种货物
 */
@RestController
@RequestMapping("stock/onestock")
public class OnestockController {
    @Autowired
    StockTypeShelfRelationService stockTypeShelfRelationService;
    @Autowired
    ShelfService shelfService;
    @Autowired
    private OnestockService onestockService;

    /**
     * 查询stockid下的所有详情
     */
    @RequestMapping("{stockId}/info")
    public R millinfo(@PathVariable("stockId") Long stockId) {
        List<OnestockEntity> res = onestockService.getByStockId(stockId);

        return R.ok().put("data", res);
    }

    /**
     * 过渡方法,处理前端数据再返会前端
     */
    @RequestMapping("/oneinfo")
    public R info(@RequestBody List<OneStockRespVo> vos) {
        List<OneStockRespVo> voList = new ArrayList<>();
        for (OneStockRespVo vo : vos) {
            if (vo.getValueSelect() != null && !vo.getValueSelect().equals("")) {
                StockTypeShelfRelationEntity relationEntity = stockTypeShelfRelationService.getOne(new QueryWrapper<StockTypeShelfRelationEntity>().eq("stock_type_id", vo.getStockTypeId()));
                ShelfEntity shelfEntity = shelfService.getOne(new QueryWrapper<ShelfEntity>().eq("shelf_id", relationEntity.getShelfId()));
                OneStockRespVo oneStockRespVo = new OneStockRespVo();
                BeanUtils.copyProperties(vo, oneStockRespVo);
                oneStockRespVo.setShelfName(shelfEntity.getShelfName());
                voList.add(oneStockRespVo);
            }
        }
        return R.ok().put("data", voList);
    }

    /**
     * 列表,检索查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = onestockService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    /**
     * 上架onestock，并向wms中添加warestock库存
     */
    @RequestMapping("/{onestockId}/up")
    public R up(@PathVariable("onestockId") Long onestockId) {

        try {
            onestockService.up(onestockId);
        } catch (Exception exception) {
            return R.error(BizCodeEnum.ONESTOCKUP_FAIL.getCode(), BizCodeEnum.ONESTOCKUP_FAIL.getMsg());
        }

        return R.ok();
    }


    /**
     * 信息,远程调用
     */
    @RequestMapping("/info/{onestockId}")
    public R info(@PathVariable("onestockId") Long onestockId) {
        OnestockEntity onestock = onestockService.getById(onestockId);

        return R.ok().put("data", onestock);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OnestockEntity onestock) {
        onestockService.save(onestock);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OnestockEntity onestock) {
        onestockService.updateById(onestock);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] onestockIds) {
        onestockService.removeByIds(Arrays.asList(onestockIds));

        return R.ok();
    }

}
