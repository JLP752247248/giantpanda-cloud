package com.panda.auth.service;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-08  10:50
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class TokenService {
    private Map<String, Authentication> authenticationMap = new HashMap<>();

    public String generateToken(Authentication authentication) {
        String md5 = Md5Crypt.md5Crypt(authentication.toString().getBytes());
        return md5;
    }

    public Authentication getAuthenticationByToken(String token) {

        return authenticationMap.get(token);
    }

    public void setAuthenticationByToken(String token, Authentication authentication) {
        authenticationMap.put(token, authentication);

    }


}
