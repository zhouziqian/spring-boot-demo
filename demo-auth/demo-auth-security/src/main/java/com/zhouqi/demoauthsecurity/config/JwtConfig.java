package com.zhouqi.demoauthsecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
    private String key;
    private Long ttl;
    private Long remember;
}
