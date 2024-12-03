package com.yuand.smarteye.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.to.LowWarnCountTo;
import com.yuand.common.to.WareStockTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.ware.entity.OnestockupTaskEntity;
import com.yuand.smarteye.ware.entity.OutbillTaskEntity;
import com.yuand.smarteye.ware.entity.WarestockEntity;
import com.yuand.smarteye.ware.exception.NoStockException;
import com.yuand.smarteye.ware.exception.NullWareStock;
import com.yuand.smarteye.ware.vo.CatalogueVo;
import com.yuand.smarteye.ware.vo.OutbilldetailVo;

import java.util.List;
import java.util.Map;

/**
 * 库存品
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-08-29 14:13:03
 */
public interface WarestockService extends IService<WarestockEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //向wms中添加warestock库存
    void add(WareStockTo wareStockTo);

    //条件查询
    PageUtils queryPageByCondition(Map<String, Object> params);

    //返回低于预警库存的货物id、valueSelect
    List<LowWarnCountTo> getLowWarnCount();

    //返回低于预警库存的货物
    PageUtils getDangerCount(Map<String, Object> params);

    /**
     * 生成出库单，立即减去库存
     */
    void billOut(List<OutbilldetailVo> vos) throws NoStockException, NullWareStock;

    /**
     * 出库可供选择目录
     */
    List<CatalogueVo> listWithGroup(int group);

    //根据wlid、shelfname、valueslect确定唯一库存,并查出剩余库存量
    Integer selectCount(Long wlId, String shelfName, String valueSelect);

    //解锁库存
    void unlock(List<OutbillTaskEntity> list);

    //处理上架oenstock的消息，验证是不是要取消库存增加
    void downStock(OnestockupTaskEntity onestockupTaskEntity);
}

