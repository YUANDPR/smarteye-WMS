package com.yuand.smarteye.purchase.feign;

import com.yuand.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("smarteye-ware")
public interface WareStockFeign {
    //返回低于预警库存的货物id、valueSelect
    @RequestMapping("/ware/warestock/warnCount")
    public R warnCount();
}
