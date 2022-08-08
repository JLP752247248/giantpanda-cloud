package com.panda.auth.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.panda.auth.config.authorization.AnyMatchBased;
import com.panda.auth.config.authorization.CustomFilterSecurityInterceptor;
import com.panda.auth.config.authorization.JwtAuthenticationTokenFilter;
import com.panda.auth.config.authorization.RealFilterInvocationSecurityMetadataSource;
import com.panda.auth.util.TokenUtil;
import com.panda.common.mvc.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-01  11:19
 * @Description: 登录验证配置
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private final static String DEFAULT_REMEMBER_ME_KEY = "default remember me key";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login/oauth2")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll().and()
                .rememberMe()
                .key(DEFAULT_REMEMBER_ME_KEY)
                .rememberMeServices(persistentTokenBasedRememberMeServices())
                .tokenValiditySeconds(14 * 24 * 60 * 60)
        ;
        //加入自定义的权限拦截器
        http.addFilterAfter(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterAfter(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    /**
     * 登陆成功后，生成token信息，并返回给前端
     * @return
     */
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler authenticationSuccessHandler = new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String md5 = "";
                try {
                    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                    md5 = MD5Utils.md5Hex(token.toString().getBytes());
                    TokenUtil.tokenMap.put(md5, token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.setContentType("application/json;charset=utf-8");
                Response<String> resp =Response.createSuc(md5,"登录成功");
                response.getWriter().write(JSONObject.toJSONString(resp));
            }
        };
        return authenticationSuccessHandler;
    }


    @Autowired
    private UserDetailsService userDetailsService;

    private RememberMeServices persistentTokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(DEFAULT_REMEMBER_ME_KEY, userDetailsService);
        return tokenBasedRememberMeServices;
    }

    private OncePerRequestFilter jwtAuthTokenFilter() {
        JwtAuthenticationTokenFilter jwtAuthTokenFilter = new JwtAuthenticationTokenFilter();
        return jwtAuthTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
        CustomFilterSecurityInterceptor filterSecurityInterceptor = new CustomFilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(new RealFilterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(new AnyMatchBased());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        return filterSecurityInterceptor;
    }

}
