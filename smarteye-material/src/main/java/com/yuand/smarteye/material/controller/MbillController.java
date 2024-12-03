package com.yuand.smarteye.material.controller;

import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.R;
import com.yuand.smarteye.material.entity.MbillEntity;
import com.yuand.smarteye.material.service.MbillService;
import com.yuand.smarteye.material.vo.MouthMbillRespVo;
import com.yuand.smarteye.material.vo.MouthMbillbingtuVo;
import com.yuand.smarteye.material.vo.savembillvo.MbillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 物料申请单
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-01 19:48:53
 */
@RestController
@RequestMapping("material/mbill")
public class MbillController {
    @Autowired
    private MbillService mbillService;

    /**
     * 查询近一个月物料申请单来自的分库的分布（饼图）
     */
    @RequestMapping("/mouthmbillbingtu")
    public R mouthmbillbingtu() {
        //查询近一个月物料申请单来自的分库的分布（饼图）
        List<MouthMbillbingtuVo> data = mbillService.queryMouthMbillbingtu();

        return R.ok().put("data", data);
    }

    /**
     * 查询近一个月物料申请单的数量趋势，以天为分组
     */
    @RequestMapping("/mouthmbill")
    public R mouthmbill() {
        //查询近一个月物料申请单的数量趋势，以天为分组
        List<MouthMbillRespVo> data = mbillService.queryMouthMbill();

        return R.ok().put("data", data);
    }


    /**
     * 查询今天新增物料申请单数量、待处理的物料申请单数量
     */
    @RequestMapping("/todaymbill")
    public R todaymbill() {
        //天新增物料申请单数量
        int taday = mbillService.queryTodayMbill();
        //待处理的物料申请单数量
        int todo = mbillService.queryTodoMbill();

        return R.ok().put("taday", taday).put("todo", todo);
    }


    /**
     * 保存申请表、申请表详情
     */
    @RequestMapping("/save")
    public R save(@RequestBody MbillVo mbillVo) {
        mbillService.saveInfo(mbillVo);

        return R.ok();
    }

    /**
     * 撤回申请
     */
    @RequestMapping("/{mbillId}/revoke")
    public R revoke(@PathVariable("mbillId") Long mbillId) {
        //返回0代表撤回失败,1代表成功
        int Res = mbillService.revoke(mbillId);

        return R.ok().put("data", Res);
    }

    /**
     * 审批物料申请，路径变量status 1为处理中，3为拒绝申请
     */
    @RequestMapping("/{mbillId}/handle/{status}")
    public R handle(@PathVariable("mbillId") Long mbillId, @PathVariable("status") int status) {
        //返回0代表失败,1代表成功
        int Res = mbillService.handle(mbillId, status);

        return R.ok().put("data", Res);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = mbillService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息 1
     */
    @RequestMapping("/info/{mbillId}")
    public R info(@PathVariable("mbillId") Long mbillId) {
        MbillEntity mbill = mbillService.getById(mbillId);

        return R.ok().put("mbill", mbill);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MbillEntity mbill) {
        mbillService.updateById(mbill);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] mbillIds) {
        mbillService.removeByIds(Arrays.asList(mbillIds));

        return R.ok();
    }

}
