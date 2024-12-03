package com.yuand.smarteye.third.service;

/**
 * SMS服务接口，定义了发送短信的相关方法
 */
public interface SmsService {

    /**
     * 发送验证码短信
     *
     * @param phone 接收短信的手机号码
     * @param code 验证码
     * @throws Exception 发送短信过程中出现的异常
     */
    void send(String phone, String code) throws Exception;

    /**
     * 发送短信内容，预警库存
     *
     * @param phone 接收短信的手机号码
     * @param msg 要发送的短信内容，通常包含预警信息
     */
    void sendWarnInfo(String phone, String msg);
}

