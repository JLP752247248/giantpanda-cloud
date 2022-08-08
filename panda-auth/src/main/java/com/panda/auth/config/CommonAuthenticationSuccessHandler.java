package com.panda.auth.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.panda.auth.util.TokenUtil;
import com.panda.common.mvc.Response;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class CommonAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {


    /**
     * 判断用户来源
     *
     * 如果是oauth2.0 用户登陆，判断是否首次登陆，首次登陆需要生成用户信息，并要求绑定手机号，做手机号验证
     * 手机号登陆后完成注册并登陆成功
     *
     *
     * 放入缓存
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
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
}
