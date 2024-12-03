package com.yuand.common.constant;



/**
 * RabbitMQ常量类
 */
public class RabbitMQConstant {

    // ware服务
    /**
     * ware服务使用的交换机名称
     */
    public static final String WARE_EXCHANGE = "ware-exchange";

    /**
     * ware服务使用的延迟队列名称
     */
    public static final String WARE_DELAYQUUEUE = "ware.delay.queue";

    /**
     * ware服务使用的延迟队列路由键
     */
    public static final String WARE_DELAYTOUTINGKEY = "ware.delay.key";

    /**
     * ware服务使用的释放库存队列名称
     */
    public static final String WARE_RELEASEQUEUE = "ware.release.queue";

    /**
     * ware服务使用的释放库存队列路由键
     */
    public static final String WARE_RELEASEROUTINGKEY = "ware.release.key";

    /**
     * ware服务使用的单个库存增加队列名称
     */
    public static final String WARE_ONESTOCKUPQUEUE = "ware.onestockup.queue";

    /**
     * ware服务使用的单个库存增加队列路由键
     */
    public static final String WARE_ONESTOCKUPROUTINGKEY = "ware.onestockup.key";

    /**
     * ware服务使用的基础路由键模式
     */
    public static final String WARE_BASEROUTINGKEY = "ware.#";

    // stock服务
    /**
     * stock服务使用的交换机名称
     */
    public static final String STOCK_EXCHANGE = "stock-exchange";

    /**
     * stock服务使用的增加库存队列名称
     */
    public static final String STOCK_UPSTOCKQUEUE = "stock.upstock.queue";

    /**
     * stock服务使用的增加库存队列路由键
     */
    public static final String STOCK_UPSTOCKROUTINGKEY = "stock.upstock.key";

}

