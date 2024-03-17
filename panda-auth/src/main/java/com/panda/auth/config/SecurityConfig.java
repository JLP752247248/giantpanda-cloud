package com.panda.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;


/**
 * @Author: JLP
 * @CreateTime: 2022-08-01  11:19
 * @Description: 登录验证配置
 * @Version: 1.0
 */
@Configuration
public class SecurityConfig {

    //https://docs.spring.io/spring-security/reference/5.6-SNAPSHOT/servlet/authorization/authorize-http-requests.html
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .mvcMatchers("/resources/**", "/signup", "/about").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/db/**").access((authentication, request) ->
                        Optional.of(hasRole("ADMIN").check(authentication, request))
                                .filter((decision) -> !decision.isGranted())
                                .orElseGet(() -> hasRole("DBA").check(authentication, request))
                ).anyRequest().denyAll()

        ).formLogin();
        return http.build();

    }
}