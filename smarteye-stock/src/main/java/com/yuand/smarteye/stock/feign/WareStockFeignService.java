package com.yuand.smarteye.stock.feign;

import com.yuand.common.to.WareStockTo;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.entity.OutbilldetailEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("smarteye-ware")
public interface WareStockFeignService {

    /**
     * 向wms中添加warestock库存
     */
    @RequestMapping("/ware/warestock/add")
    public R add(@RequestBody WareStockTo wareStockTo);

    /**
     * 生成出库单，立即锁库存
     */
    @RequestMapping("/ware/warestock/billout")
    public R billOut(@RequestBody List<OutbilldetailEntity> vos);


}
