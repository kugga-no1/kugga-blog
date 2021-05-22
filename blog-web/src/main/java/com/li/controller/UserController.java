package com.li.controller;


import com.li.Result;
import com.li.ResultInfo;
import com.li.api.RoleService;
import com.li.api.UserService;
import com.li.dto.UserDTO;
import com.li.pojo.User;
import com.li.service.impl.auth.MyUserDetails;
import com.li.service.impl.auth.MyUserDetailsService;
import com.li.vo.UserQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/getUserList")
    public Result getUserList(UserQueryVO userQueryVO){
        if(userQueryVO!=null){
        userQueryVO.setCurrent((userQueryVO.getCurrent()-1)*userQueryVO.getSize());  //比如五条一页 那第一页是（（1-1）*5）开始数五条 第二页（（2-1）*5）
        List<UserDTO> userList=userService.queryUserByConditions(userQueryVO);
        int total=userService.count(userQueryVO);
        return Result.success().data("userList",userList).data("total",total);
             }
        return Result.error().CodeAndeMessage(ResultInfo.QUERYVO_NULL);
    }
}

