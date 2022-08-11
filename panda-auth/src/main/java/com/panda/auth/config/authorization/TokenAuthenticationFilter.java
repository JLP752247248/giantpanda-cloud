package com.panda.auth.config.authorization;

import com.panda.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token拦截器
 * 使用Spring提供的OncePerRequestFilter保证单次
 */
//@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            //没有token 放行 后续过滤器会进行校验
            filterChain.doFilter(request,response);
            return;
        }

        Authentication authenticationToken = tokenService.getAuthenticationByToken(token);
        if(authenticationToken == null){
            //没有token 放行 后续过滤器会进行校验
            filterChain.doFilter(request,response);
            return;
        }
        //获取用户信息


        //UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}