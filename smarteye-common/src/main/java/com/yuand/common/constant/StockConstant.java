package com.yuand.common.constant;

import lombok.Getter;

/**
 * 库存模块的相关枚举
 */
public class StockConstant {

    /**
     * 库存类型枚举
     */
    @Getter
    public enum StockTypeEnum {
        /**
         * 基本货物类型
         */
        STOCK_TYPE_TYPE_BASE(1, "基本货物"),
        /**
         * 货架外货物类型
         */
        STOCK_TYPE_TYPE_OUT(0, "货架外货物");

        private final int code;
        private final String msg;


        StockTypeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

    /**
     * 出库单状态枚举
     */
    @Getter
    public enum OutbillStatusEnum {
        /**
         * 新建状态
         */
        CREATE(0, "新建"),
        /**
         * 已完成状态
         */
        FINISH(1, "已完成");

        private final int code;
        private final String msg;


        OutbillStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }


    }

    /**
     * 出库单详情状态枚举
     */
    @Getter
    public enum OutbilldetailStatusEnum {
        /**
         * 新建状态
         */
        CREATE(0, "新建"),
        /**
         * 已完成状态
         */
        FINISH(1, "已完成");

        private final int code;
        private final String msg;


        OutbilldetailStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }


    }

    /**
     * 出库单详情类型枚举
     */
    @Getter
    public enum OutbilldetailEnum {
        /**
         * 物料申请单出库类型
         */
        APPLY_BILL(1, "物料申请单出库"),
        /**
         * 手动出库类型
         */
        ARTIFICIAL(2, "手动出库"),
        /**
         * 其他出库类型
         */
        OTHER(3, "其他出库");

        private final int code;
        private final String msg;


        OutbilldetailEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }
}
