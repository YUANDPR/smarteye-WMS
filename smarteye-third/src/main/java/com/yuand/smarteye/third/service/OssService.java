package com.yuand.smarteye.third.service;

import java.util.Map;


/**
 * 对象存储服务接口，用于生成签名策略
 */
public interface OssService {

    /**
     * 生成签名策略
     *
     * @return 签名策略的键值对映射
     */
    Map<String, String> policy();
}


