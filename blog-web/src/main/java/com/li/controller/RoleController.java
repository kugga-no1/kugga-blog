package com.li.controller;


import com.li.Result;
import com.li.api.RoleService;
import com.li.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoleList")
    public Result test2(){
        List<RoleDTO> userRoleList=roleService.getRole();
        return Result.success().data("userRoleList",userRoleList);
    }
}

