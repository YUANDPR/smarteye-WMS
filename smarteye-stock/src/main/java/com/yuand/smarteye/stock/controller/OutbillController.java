package com.yuand.smarteye.stock.controller;

import com.yuand.common.constant.RedisConstant;
import com.yuand.common.exception.BizCodeEnum;
import com.yuand.common.to.EmployeeEntityTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.OutbillEntity;
import com.yuand.smarteye.stock.exception.LockStockException;
import com.yuand.smarteye.stock.exception.MiTokenException;
import com.yuand.smarteye.stock.interceptor.LoginUserInterceptor;
import com.yuand.smarteye.stock.service.OutbillService;
import com.yuand.smarteye.stock.vo.saveoutbillvo.OutbillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 出库单
 */
@RestController
@RequestMapping("stock/outbill")
public class OutbillController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private OutbillService outbillService;

    /**
     * 查询outbillid的出库单信息
     */
    @RequestMapping("/{outbillId}/outbillinfo")
    public R outbillinfo(@PathVariable("outbillId") Long outbillId) {
        OutbillEntity res = outbillService.getInfo(outbillId);

        return R.ok().put("data", res);
    }

    /**
     * 查询今天新增出库单数量、待处理的出库单数量
     */
    @RequestMapping("/todayoutbill")
    public R todayoutbill() {
        //今天新增出库单数量
        int taday = outbillService.queryTodayOutbill();
        //待处理的出库单数量
        int todo = outbillService.queryTodoOutbill();
        //毕测试
        taday = 2;
        //毕测试
        todo = 3;
        return R.ok().put("taday", taday).put("todo", todo);
    }

    /**
     * 给前端一个用于幂等性的token防重令牌
     */
    @RequestMapping("/getToken")
    public R getToken() {
        String uuid = UUID.randomUUID().toString().replace("_", "").substring(0, 5);
        EmployeeEntityTo employeeEntityTo = LoginUserInterceptor.threadLocal.get();
        //在redis中存入幂等token防重令牌用于验证
        redisTemplate.opsForValue().set(RedisConstant.MITOKEN + employeeEntityTo.getEeId(), uuid, 10, TimeUnit.MINUTES);
        //前端存一份token，后续请求一起发过来验证幂等
        return R.ok().put("data", uuid);
    }

    /**
     * 保存出库单及出库详情,
     * 新建库存单的同时立即锁定对应库存，此时库存虽然没取走，但是后续出库单无法再出库这部分库存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OutbillVo outbillVo) {
        //1.保存出库单及出库详情
        try {
            outbillService.saveInfo(outbillVo);
        } catch (LockStockException e) { //锁库存异常
            return R.error(BizCodeEnum.OUTBILL_FAIL.getCode(), BizCodeEnum.OUTBILL_FAIL.getMsg());
        } catch (MiTokenException e) {  //幂等验证失败
            return R.error(BizCodeEnum.MITOKEN_FAIL.getCode(), BizCodeEnum.MITOKEN_FAIL.getMsg());
        }

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = outbillService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{outbillId}")
    public R info(@PathVariable("outbillId") Long outbillId) {
        OutbillEntity outbill = outbillService.getById(outbillId);

        return R.ok().put("data", outbill);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OutbillEntity outbill) {
        outbillService.updateById(outbill);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] outbillIds) {
        outbillService.removeByIds(Arrays.asList(outbillIds));

        return R.ok();
    }

}
