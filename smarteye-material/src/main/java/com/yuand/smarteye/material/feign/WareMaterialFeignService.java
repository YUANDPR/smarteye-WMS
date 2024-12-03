package com.yuand.smarteye.material.feign;

import com.yuand.common.to.WareMaterialTo;
import com.yuand.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("smarteye-ware")
public interface WareMaterialFeignService {

    /**
     * 向wms中添加warematerial库存(分库库存)
     */
    @RequestMapping("/ware/warematerial/add")
    public R add(@RequestBody WareMaterialTo wareMaterialTo);

}
