package com.zhouqi.demoauthsecurity.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@Configuration
public class IdConfig {
    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(1, 1);
    }
}
