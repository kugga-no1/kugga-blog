package com.li.service.impl.auth;

import com.li.api.UserService;
import com.li.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.isEmpty()){
            throw new UsernameNotFoundException("用户名为空");
        }

        User user=userService.getUserByUserName(username);
        if(user!=null){
            List<String> roles = userService.getUserRoleByUserId(user.getId());
            List<GrantedAuthority> authorities=new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority=null;
            for(String role:roles){
                simpleGrantedAuthority =new SimpleGrantedAuthority(role);
                authorities.add(simpleGrantedAuthority);
            }

           // myUserDetails.getUser().setPassword("{noop}"+myUserDetails.getUser().getPassword()); 不加密密码
            return new MyUserDetails(user,authorities);
        }
        else {
            throw new UsernameNotFoundException("没有用户");
        }
    }
}
