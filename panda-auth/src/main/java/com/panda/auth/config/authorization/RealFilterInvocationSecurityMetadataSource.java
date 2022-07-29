package com.panda.auth.config.authorization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义权限拦截器权限数据获取数据源
 */
public class RealFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    protected final Log logger = LogFactory.getLog(getClass());


    private Map<RequestMatcher, Collection<ConfigAttribute>> getReMap(){
        Map<String, Set<String>> urlRoleMap = new HashMap<>();
        Set<String> testRoles =new HashSet<>();
        testRoles.add("XXX");
        testRoles.add("XXY");
        testRoles.add("XXZ");
        urlRoleMap.put("/syspros",testRoles);
        Set<String> testRoles1 =new HashSet<>();
        testRoles1.add("XXX1");
        urlRoleMap.put("/getsys",testRoles1);
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
        for(String url : urlRoleMap.keySet()) {
            Set<String> needRoles = urlRoleMap.get(url);

            // 注意此处，我们设置ConfigAttribute为 ROLE_ 前缀加上角色标识，与 CustomJdbcUserDetailsService 里面组织UserDetails设置角色标识呼应
            requestMap.put(new AntPathRequestMatcher(url), needRoles.stream().map(role -> new SecurityConfig("ROLE_" + role)).collect(Collectors.toSet()));
        }
        return requestMap;
    }

    // ~ Constructors
    // ===================================================================================================



    // ~ Methods
    // ========================================================================================================

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : getReMap()
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        //这里可以改成 从库中查询出拥有当前url权限的角色
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : getReMap()
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
