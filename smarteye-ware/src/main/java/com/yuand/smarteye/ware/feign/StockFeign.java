package com.yuand.smarteye.ware.feign;

import com.yuand.common.to.WareStockRespTo;
import com.yuand.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("smarteye-stock")
public interface StockFeign {
    /**
     * 根据wlid、wlpid、wlppid查并设置对应名称
     */
    @RequestMapping("/stock/warelocation/transform")
    public R transform(@RequestBody List<WareStockRespTo> tos);

    @RequestMapping("/stock/outbill/info/{outbillId}")
    public R info(@PathVariable("outbillId") Long outbillId);

    @RequestMapping("/info/{onestockId}")
    public R ontstockInfo(@PathVariable("onestockId") Long onestockId);
}
