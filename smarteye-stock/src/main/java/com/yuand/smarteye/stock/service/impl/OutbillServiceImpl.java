package com.yuand.smarteye.stock.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuand.common.constant.RedisConstant;
import com.yuand.common.constant.StockConstant;
import com.yuand.common.to.EmployeeEntityTo;
import com.yuand.common.utils.PageUtils;
import com.yuand.common.utils.Query;
import com.yuand.common.utils.R;
import com.yuand.smarteye.stock.dao.OutbillDao;
import com.yuand.smarteye.stock.dao.OutbilldetailDao;
import com.yuand.smarteye.stock.entity.OutbillEntity;
import com.yuand.smarteye.stock.entity.OutbilldetailEntity;
import com.yuand.smarteye.stock.exception.LockStockException;
import com.yuand.smarteye.stock.exception.MiTokenException;
import com.yuand.smarteye.stock.feign.WareStockFeignService;
import com.yuand.smarteye.stock.interceptor.LoginUserInterceptor;
import com.yuand.smarteye.stock.service.OutbillService;
import com.yuand.smarteye.stock.service.WareLocationService;
import com.yuand.smarteye.stock.vo.saveoutbillvo.OutbillVo;
import com.yuand.smarteye.stock.vo.saveoutbillvo.OutbilldetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("outbillService")
public class OutbillServiceImpl extends ServiceImpl<OutbillDao, OutbillEntity> implements OutbillService {
    @Autowired
    WareLocationService wareLocationService;
    @Autowired
    OutbilldetailDao outbilldetailDao;
    @Autowired
    WareStockFeignService wareStockFeignService;
    @Autowired
    OutbillDao outbillDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OutbillEntity> page = this.page(
                new Query<OutbillEntity>().getPage(params),
                new QueryWrapper<OutbillEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存出库单及出库详情
     * 新建库存单的同时立即锁定对应库存，此时库存虽然没取走，但是后续出库单无法再出库这部分库存
     */
    //分布式事务，延时队列实现最终一致性
    @Transactional
    @Override
    public void saveInfo(OutbillVo outbillVo) throws MiTokenException, LockStockException {

        //0.原子性保证
        EmployeeEntityTo employeeEntityTo = LoginUserInterceptor.threadLocal.get();
        //  验证令牌 [必须保证原子性] 返回 0 or 1
        // 0令牌删除失败 1删除成功
        String script = "if redis.call('get',KEYS[1]) == ARGV[1]" +
                "then return redis.call('del',KEYS[1]) " +
                "else return 0 " +
                "end";
        String miToken = outbillVo.getMiToken();

        // 原子验证令牌 删除令牌
        Object result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                Arrays.asList(RedisConstant.MITOKEN + employeeEntityTo.getEeId()),
                miToken);
        if ((Long) result == 0L) {
            //幂等验证不通过直接抛出异常
            throw new MiTokenException();
        }

        //1.保存出库单
        OutbillEntity outbillEntity = new OutbillEntity();
        BeanUtils.copyProperties(outbillVo, outbillEntity);
        //设置新建状态
        outbillEntity.setStatus(StockConstant.OutbillStatusEnum.CREATE.getCode());
        //保存出库单
        outbillDao.insert(outbillEntity);

        //2.保存出库单详情
        List<OutbilldetailEntity> list = new ArrayList<>();
        for (OutbilldetailVo vo : outbillVo.getOutbilldetialVos()) {
            OutbilldetailEntity outbilldetailEntity = new OutbilldetailEntity();
            BeanUtils.copyProperties(vo, outbilldetailEntity);
            //设置对应的outbill的Id
            outbilldetailEntity.setOutbillId(outbillEntity.getOutbillId());
            //设置wlId、wlpId、wlppId
            outbilldetailEntity.setWlId(outbillEntity.getWlId());
            Long[] catelogPath = wareLocationService.findCatelogPath(outbillEntity.getWlId());
            outbilldetailEntity.setWlpId(catelogPath[1]);
            outbilldetailEntity.setWlppId(catelogPath[0]);
            //状态默认设置为新建
            outbilldetailEntity.setStatus(StockConstant.OutbilldetailStatusEnum.CREATE.getCode());
            //保存出库单详情
            //TODO 考虑是否批量插入，使用mp批量插入是否真的会降低与数据库的交互次数，mp批量操作与数据库是怎么实现的？
            outbilldetailDao.insert(outbilldetailEntity);
            //添加到list供远程调用，集中为一次远程调用
            list.add(outbilldetailEntity);
        }
        //3.立即锁定对应库存
        R r = wareStockFeignService.billOut(list);
        if (r.getCode() != 0) {
            //库存锁定失败，抛出异常回滚出库单
            throw new LockStockException();
        }

    }

    //今天新增出库单数量
    @Cacheable(value = {"outbill"}, key = "#root.method.name")
    @Override
    public int queryTodayOutbill() {
        int res = outbillDao.queryTodayOutbill();
        return res;
    }

    //待处理的出库单数量
    @Cacheable(value = {"outbill"}, key = "#root.method.name")
    @Override
    public int queryTodoOutbill() {
        int res = outbillDao.queryTodoOutbill();
        return res;
    }

    /**
     * 查询outbillid的出库单信息
     * 不频繁，无需缓存
     */
    @Override
    public OutbillEntity getInfo(Long outbillId) {
        OutbillEntity outbillEntity = outbillDao.selectById(outbillId);
        return outbillEntity;
    }

}
