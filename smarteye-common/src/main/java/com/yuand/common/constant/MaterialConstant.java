package com.yuand.common.constant;

import lombok.Getter;

/**
 * 物料模块的相关枚举
 */
public class MaterialConstant {

    /**
     * 物料类型枚举
     */
    @Getter
    public enum MaterialTypeEnum {
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

        MaterialTypeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

    /**
     * 物料申请单状态枚举
     */
    @Getter
    public enum MaterialRequestStatusEnum {
        /**
         * 申请中状态
         */
        APPLYING(0, "申请中"),
        /**
         * 处理中状态
         */
        PROCESSING(1, "处理中"),
        /**
         * 已完成状态
         */
        FINISH(2, "已完成"),
        /**
         * 拒绝申请状态
         */
        REFUSE(3, "拒绝申请"),
        /**
         * 撤回申请状态
         */
        REVOKE(4, "撤回申请");

        private final int code;
        private final String msg;

        MaterialRequestStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

    /**
     * 物料申请单详情状态枚举
     */
    @Getter
    public enum MaterialRequestDetailStatusEnum {
        /**
         * 申请中状态
         */
        APPLYING(0, "申请中"),
        /**
         * 已送达状态
         */
        RECEIVED(1, "已送达"),
        /**
         * 拒绝申请状态
         */
        REFUSE(3, "拒绝申请"),
        /**
         * 撤回申请状态
         */
        REVOKE(4, "撤回申请");

        private final int code;
        private final String msg;

        MaterialRequestDetailStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }
}

