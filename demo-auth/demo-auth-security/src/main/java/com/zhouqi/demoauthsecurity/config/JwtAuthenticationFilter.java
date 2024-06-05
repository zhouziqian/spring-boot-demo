package com.zhouqi.demoauthsecurity.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhouqi.demoauthsecurity.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpMethod.*;

/**
 * @author zhouqi9
 * date 2024/6/4
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    @Resource
//    private CustomUserDetailsService customUserDetailsService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private CustomConfig customConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    private boolean checkIgnore(HttpServletRequest request) {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        if (ObjectUtil.isNull(httpMethod)) {
            httpMethod = GET;
        }
        Set<String> ignores = new HashSet<>();
        if (httpMethod.equals(GET)) {
            ignores.addAll(customConfig.getIgnores().getGet());
        } else if (httpMethod.equals(PUT)) {
            ignores.addAll(customConfig.getIgnores().getPut());
        } else if (httpMethod.equals(HEAD)) {
            ignores.addAll(customConfig.getIgnores().getHead());
        } else if (httpMethod.equals(POST)) {
            ignores.addAll(customConfig.getIgnores().getPost());
        } else if (httpMethod.equals(PATCH)) {
            ignores.addAll(customConfig.getIgnores().getPatch());
        } else if (httpMethod.equals(TRACE)) {
            ignores.addAll(customConfig.getIgnores().getTrace());
        } else if (httpMethod.equals(DELETE)) {
            ignores.addAll(customConfig.getIgnores().getDelete());
        } else if (httpMethod.equals(OPTIONS)) {
            ignores.addAll(customConfig.getIgnores().getOptions());
        }
        ignores.addAll(customConfig.getIgnores().getPattern());
        if (CollUtil.isNotEmpty(ignores)) {
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }
        return false;
    }
}
