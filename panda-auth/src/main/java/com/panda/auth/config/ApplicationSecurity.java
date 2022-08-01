package com.panda.auth.config;

import com.panda.auth.config.authorization.AnyMatchBased;
import com.panda.auth.config.authorization.CustomFilterSecurityInterceptor;
import com.panda.auth.config.authorization.JwtAuthenticationTokenFilter;
import com.panda.auth.config.authorization.RealFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private final static String DEFAULT_REMEMBER_ME_KEY = "default remember me key";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureForwardUrl("/login/failure")
                .successForwardUrl("/login/success")
                .permitAll()
                .and()
                .logout()
                .permitAll().and()
                .rememberMe()
                .key(DEFAULT_REMEMBER_ME_KEY)
                .rememberMeServices(persistentTokenBasedRememberMeServices())
                .tokenValiditySeconds(14 * 24 * 60 * 60);
        //加入自定义的权限拦截器
        http.addFilterAfter(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterAfter(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
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
