package com.panda.auth.config.authorization;

import com.panda.auth.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 短信验证过滤器
 * 使用Spring提供的OncePerRequestFilter保证单次
 */
//@Component
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public SmsAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 获取手机号
        String code = request.getParameter("sms");
        if(!code.isEmpty()){
            SmsAuthentication smsAuthentication =new SmsAuthentication();
            SecurityContextHolder.getContext().setAuthentication(smsAuthentication);
            return smsAuthentication;
        }
        throw new RuntimeException("sdfasdf");
    }
}
