package com.panda.auth.config;

import com.panda.auth.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-08  15:50
 * @Description: 登陆成功处理器
 * @Version: 1.0
 */
@Component
@Slf4j
public class CommonAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private TokenService tokenService;

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
        try {
            log.info("登录成功");
            String token = tokenService.generateToken(authentication);
            tokenService.setAuthenticationByToken(token, authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
