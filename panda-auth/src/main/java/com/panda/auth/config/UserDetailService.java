package com.panda.auth.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户信息加载器，用于加载当前用户的基本信息，用于认证和授权
 */
@Component("userDetailsService")
public class UserDetailService implements UserDetailsService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String pwd = encoder.encode("123");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user =
                User.builder()
                        .username(username)
                        .password(pwd)
                        .roles("XXZ")
                        .build();
        return user;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s1 = encoder.encode("5000");
        String s2 = encoder.encode("5000");
        System.out.println(s1);
        System.out.println(encoder.matches("5000", s1));
        System.out.println(s2);
        System.out.println(encoder.matches("5000", s2));
    }
}
