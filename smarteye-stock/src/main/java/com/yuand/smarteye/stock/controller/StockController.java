package com.yuand.smarteye.stock.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.StockEntity;
import com.yuand.smarteye.stock.service.StockService;
import com.yuand.smarteye.stock.vo.saveVo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 待上架或已上架的一批货物
 */
@RestController
@RequestMapping("stock/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    /**
     * 查询今天新增入库单数量、待上架的入库单数量
     */
    @RequestMapping("/todaybill")
    public R todaymbill() {
        //今天新增入库单数量
        int taday = stockService.queryTodaybill();
        //待上架的入库单数量
        int todo = stockService.queryTodobill();
        //毕测试
        taday = 1;
        //毕测试
        todo = 3;
        return R.ok().put("taday", taday).put("todo", todo);
    }

    /**
     * 保存stock及onestock,入库操作
     */
    @RequestMapping("/save")
    public R save(@RequestBody StockVo stockVo) {
        stockService.saveInfo(stockVo);

        return R.ok();
    }

    /**
     * 检索查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = stockService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 上架stock
     */
    @RequestMapping("/{stockId}/up")
    public R up(@PathVariable("stockId") Long stockId) {
        //返回0代表onestock没全部上架stock上架失败,1代表stock上架成功
        int upRes = stockService.up(stockId);

        return R.ok().put("data", upRes);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{stockId}")
    public R info(@PathVariable("stockId") Long stockId) {
        StockEntity stock = stockService.getById(stockId);

        return R.ok().put("stock", stock);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StockEntity stock) {
        stockService.updateById(stock);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] stockIds) {
        stockService.removeByIds(Arrays.asList(stockIds));

        return R.ok();
    }

}
