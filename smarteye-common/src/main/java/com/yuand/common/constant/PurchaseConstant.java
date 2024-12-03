package com.yuand.common.constant;

import lombok.Getter;

/**
 * 采购模块的相关枚举
 */
public class PurchaseConstant {

    /**
     * 采购需求枚举
     */
    @Getter
    public enum BuyDemandStatusEnum {
        /**
         * 新建状态
         */
        CREATED(0, "新建"),
        /**
         * 已分配状态
         */
        ASSIGNED(1, "已分配"),
        /**
         * 正在采购状态
         */
        BUYING(2, "正在采购"),
        /**
         * 已完成状态
         */
        FINISH(3, "已完成"),
        /**
         * 采购失败状态
         */
        ERROR(4, "采购失败");

        private final int code;
        private final String msg;

        BuyDemandStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

    /**
     * 采购单状态枚举
     */
    @Getter
    public enum BuyListStatusEnum {
        /**
         * 新建状态
         */
        CREATED(0, "新建"),
        /**
         * 已分配状态
         */
        ASSIGNED(1, "已分配"),
        /**
         * 已领取状态
         */
        RECEIVE(2, "已领取"),
        /**
         * 已完成状态
         */
        FINISH(3, "已完成"),
        /**
         * 有异常状态
         */
        ERROR(4, "有异常");

        private final int code;
        private final String msg;

        BuyListStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }


}
