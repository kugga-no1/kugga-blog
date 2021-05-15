package com.li.controller;


import com.li.api.UserService;
import com.li.service.impl.auth.MyUserDetails;
import com.li.service.impl.auth.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kugga
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        return userService.getUserByUserName("admin").toString();
    }

    @RequestMapping("/test2")
    public String test2(){

        return userService.checkPassWord("admin","123456")==true?"ture":"false";
    }

}

