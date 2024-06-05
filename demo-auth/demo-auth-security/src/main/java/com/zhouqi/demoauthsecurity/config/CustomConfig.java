package com.zhouqi.demoauthsecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {
    private IgnoreConfig ignores;
}
