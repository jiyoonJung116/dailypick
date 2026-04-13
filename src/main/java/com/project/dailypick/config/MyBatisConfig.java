package com.project.dailypick.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.project.dailypick.mapper")
public class MyBatisConfig {
}
