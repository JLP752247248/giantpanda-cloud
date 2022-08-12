package com.panda.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-11  12:52
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class OAuthAuthenticationSuccessHandler extends CommonAuthenticationSuccessHandler {


    /**
     * 判断用户来源
     * <p>
     * 如果是oauth2.0 用户登陆，判断是否首次登陆，首次登陆需要生成用户信息，并要求绑定手机号，做手机号验证
     * 手机号登陆后完成注册并登陆成功
     * <p>
     * <p>
     * 放入缓存
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
