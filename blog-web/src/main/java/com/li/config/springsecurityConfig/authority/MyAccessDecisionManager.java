package com.li.config.springsecurityConfig.authority;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class  MyAccessDecisionManager implements AccessDecisionManager {
    private boolean supports=true;
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //如果没有需要的权限信息 放行
        if(configAttributes==null){
            return;
        }
        if(!authentication.isAuthenticated()){
            throw new AccessDeniedException("未登录");
        }

        //获取登陆用户的权限信息
        Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
        for(GrantedAuthority grantedAuthority:collection){
            String authority=grantedAuthority.getAuthority();
            //获取所需要的权限信息
            for(ConfigAttribute configAttribute:configAttributes) {
                System.out.println("authority：" +authority);
                System.out.println("configattribute:"+configAttribute);
                if (authority.equals(configAttribute.getAttribute())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return supports;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return supports;
    }
}
