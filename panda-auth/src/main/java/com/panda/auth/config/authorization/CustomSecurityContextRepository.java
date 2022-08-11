package com.panda.auth.config.authorization;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-11  16:15
 * @Description: TODO
 * @Version: 1.0
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的securitycontext仓库
 * <br/>
 * 分布式session
 */
public class CustomSecurityContextRepository implements SecurityContextRepository {

    //@TODO 后面可以改成分布式缓存
    private Map<String,SecurityContext> map =new HashMap<>();
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        SecurityContext context = readSecurityContext(request);
        if (context == null) {
            context = SecurityContextHolder.createEmptyContext();
        }

        return context;
    }

    private SecurityContext readSecurityContext(HttpServletRequest request) {
        // 从cookie或header或params中获取代表当前登录的唯一标识，如token
        // 获取context，如分布式session
        // TODO 自定义类，field：context expireTime ..
        // TODO 验证超时

        Cookie token = WebUtils.getCookie(request, "JSESSIONID");
        return  map.get(token.getValue());
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        Cookie token = WebUtils.getCookie(request, "JSESSIONID");
        map.put(token.getValue(),context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        // 从cookie或header或params中获取代表当前登录的唯一标识，如token
        // 判断key是否存在，如分布式session
        Cookie token = WebUtils.getCookie(request, "JSESSIONID");
        if (null != token) {
            String tokenKey = token.getValue();
            return map.containsKey(tokenKey);
        }

        return false;
    }
}