package com.yuand.smarteye.third.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信服务配置
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Configuration
public class MySmsConfig {

    private String accessKeyId;

    private String accessKeySecret;

    @Bean
    public Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);

        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";

        return new Client(config);
    }

}
