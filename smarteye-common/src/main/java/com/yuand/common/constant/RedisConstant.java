package com.yuand.common.constant;

/**
 * Redis缓存的常量类
 */
public class RedisConstant {

    /**
     * 短信验证码缓存的键前缀
     */
    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";

    /**
     * 会话标识的键
     */
    public static final String SESSION = "SMARTEYESESSION";

    /**
     * 通用缓存数据的键前缀
     */
    public static final String CACHE = "cache:";

    /**
     * 用户身份令牌的键前缀
     */
    public static final String MITOKEN = "mitoken:";
}

