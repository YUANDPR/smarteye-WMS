/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.yuand.auth.service.impl;


import com.google.code.kaptcha.Producer;
import com.yuand.auth.exception.NoUUIDException;
import com.yuand.auth.service.CaptchaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 */
@Service("CaptchaService")
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            //uuid为空抛出异常
            throw new NoUUIDException();
        }
        //生成文字验证码
        String code = producer.createText();
        //存redis,并设置一分钟过期
        stringRedisTemplate.opsForValue().set(uuid.substring(0, 7), code, 60, TimeUnit.SECONDS);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        String uuidRedis = stringRedisTemplate.opsForValue().get(uuid.substring(0, 7));
        if (StringUtils.isEmpty(uuidRedis) || !uuidRedis.equals(code)) {
            return false;
        }

        //删除验证码（保证验证码只用一次）
        stringRedisTemplate.delete(uuid);

        return true;
    }
}
