package com.zhouqi.demoauthsecurity.util;

import com.zhouqi.demoauthsecurity.common.Const;
import com.zhouqi.demoauthsecurity.config.JwtConfig;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil {
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建Jwt
     *
     * @param rememberMe  记住我
     * @param id          用户id
     * @param subject     用户名
     * @param roles       用户角色
     * @param authorities 用户权限
     * @return Jwt
     */
    public String createJwt(Boolean rememberMe, Long id, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(id.toString()).setSubject(subject).setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey()).claim("roles", roles)
                .claim("authorities", authorities);
        // 设置过期时间
        Long ttl = rememberMe ? jwtConfig.getRemember() : jwtConfig.getTtl();
        if (ttl > 0) {
            builder.setExpiration(new Date(now.getTime() + ttl));
        }
        String jwt = builder.compact();
        stringRedisTemplate.opsForValue().set(Const.REDIS_JWT_KEY_PREFIX + subject, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }


}
