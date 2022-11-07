package com.yxz.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaozhuo
 * @date 2022/11/07
 */
@Configuration
public class FeiginConfig {
    @Bean
    public Logger.Level serLogerLevel() {
        return Logger.Level.FULL;
    }
}
