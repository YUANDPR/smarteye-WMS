package com.yuand.auth.config;


import com.yuand.common.constant.RedisConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class MySessionConfig {

    /**
     * 配置SpringSession的Redis序列化方式
     * 使用JSON序列化以便在Redis中存储会话信息
     * 注意这里的RedisSerializer、CookieSerializer都是SpringSession包下的，不是原生的
     *
     * @return RedisSerializer 实例
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * 配置SpringSession的Cookie设置
     * 通过定制Cookie序列化器来管理会话Cookie的生成和解析
     *
     * @return CookieSerializer 实例
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName(RedisConstant.SESSION); //修改cookie的key名称
        serializer.setUseHttpOnlyCookie(false); //允许前端js使用该cookie
        //TODO 部署的时候我没有域名备案，得用ip访问，这里得改
        //serializer.setDomainName("smarteye.com"); // 扩大cookie的有效域
        return serializer;
    }
}

