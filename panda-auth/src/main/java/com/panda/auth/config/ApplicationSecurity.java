package com.panda.auth.config;

import com.panda.auth.config.authorization.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.filter.OncePerRequestFilter;

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

    @Autowired
    private CommonAuthenticationSuccessHandler commonAuthenticationSuccessHandler;

    @Autowired
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                //.formLogin()
                .oauth2Login()
                .loginPage("/login/oauth2")
                .successHandler(oAuthAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login/oauth2")
                .loginProcessingUrl("/login/form")
                .successHandler(commonAuthenticationSuccessHandler)
                .and()
                .logout()
                .permitAll().and()
                .rememberMe()
                .key(DEFAULT_REMEMBER_ME_KEY)
                .rememberMeServices(persistentTokenBasedRememberMeServices())
                .tokenValiditySeconds(14 * 24 * 60 * 60)
                .and().securityContext().securityContextRepository(new CustomSecurityContextRepository())

        ;
        //加入自定义的权限拦截器
        http.addFilterAfter(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //http.addFilterAfter(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }


    private RememberMeServices persistentTokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(DEFAULT_REMEMBER_ME_KEY, userDetailsService);
        return tokenBasedRememberMeServices;
    }

    private OncePerRequestFilter jwtAuthTokenFilter() {
        TokenAuthenticationFilter jwtAuthTokenFilter = new TokenAuthenticationFilter();
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
