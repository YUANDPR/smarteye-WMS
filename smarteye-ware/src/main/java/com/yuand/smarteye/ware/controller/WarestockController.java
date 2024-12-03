package com.yuand.smarteye.ware.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuand.common.exception.BizCodeEnum;
import com.yuand.common.to.LowWarnCountTo;
import com.yuand.common.to.WareStockTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.ware.dao.WarestockDao;
import com.yuand.smarteye.ware.entity.WarestockEntity;
import com.yuand.smarteye.ware.exception.NoStockException;
import com.yuand.smarteye.ware.exception.NullWareStock;
import com.yuand.smarteye.ware.service.WarestockService;
import com.yuand.smarteye.ware.vo.OutbilldetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 库存品
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-29 14:13:03
 */
@RestController
@RequestMapping("ware/warestock")
public class WarestockController {
    @Autowired
    WarestockDao warestockDao;
    @Autowired
    private WarestockService warestockService;

    //测试
    @RequestMapping("/a")
    public R ad() {
        QueryWrapper<WarestockEntity> queryWrapper = new QueryWrapper<WarestockEntity>().groupBy("shelf_name");
        List<Map<String, Object>> maps = warestockDao.selectMaps(queryWrapper);
        for (Map map : maps) {
            System.out.println(map);
        }

        return R.ok();
    }

    /**
     * 向wms中添加warestock库存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WareStockTo wareStockTo) {
        warestockService.add(wareStockTo);
        return R.ok();
    }


    /**
     * 列表,条件查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {

        PageUtils page = warestockService.queryPageByCondition(params);


        return R.ok().put("page", page);
    }

    /**
     * 返回低于预警库存的货物
     */
    @RequestMapping("/dangerCount")
    public R warnCount(@RequestParam Map<String, Object> params) {
        PageUtils page = warestockService.getDangerCount(params);

        return R.ok().put("page", page);
    }

    /**
     * 返回低于预警库存的货物id、valueSelect
     */
    @RequestMapping("/warnCount")
    public R warnCount() {
        List<LowWarnCountTo> tos = warestockService.getLowWarnCount();

        return R.ok().put("data", tos);
    }

    /**
     * 生成出库单，立即锁定库存
     */
    @RequestMapping("/billout")
    public R billOut(@RequestBody List<OutbilldetailVo> vos) {
        try {
            warestockService.billOut(vos);
        } catch (NoStockException e) {
            return R.error(BizCodeEnum.NOSTOCK_EXCEPTION.getCode(), BizCodeEnum.NOSTOCK_EXCEPTION.getMsg());
        } catch (NullWareStock nullWareStock) {
            return R.error(BizCodeEnum.NULLWARESTOCK_EXCEPTION.getCode(), BizCodeEnum.NULLWARESTOCK_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    /**
     * 出库显示库存余量
     */
    @RequestMapping("/count")
    public R count(@Param("wlId") Long wlId, @Param("shelfName") String shelfName, @Param("valueSelect") String valueSelect) {
        //根据wlid、shelfname、valueslect确定唯一库存,并查出剩余库存量
        Integer count = warestockService.selectCount(wlId, shelfName, valueSelect);
        return R.ok().put("data", count);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{warestockId}")
    public R info(@PathVariable("warestockId") Long warestockId) {
        WarestockEntity warestock = warestockService.getById(warestockId);

        return R.ok().put("data", warestock);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WarestockEntity warestock) {
        warestockService.save(warestock);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WarestockEntity warestock) {
        warestockService.updateById(warestock);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] warestockIds) {
        warestockService.removeByIds(Arrays.asList(warestockIds));

        return R.ok();
    }

}
