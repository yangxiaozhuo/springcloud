package com.yxz.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaozhuo
 * @date 2022/11/12
 */
@Configuration
@MapperScan({"com.yxz.springcloud.dao"})
public class MyBatisConfig {
}
