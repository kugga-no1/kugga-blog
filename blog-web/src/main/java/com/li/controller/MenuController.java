package com.li.controller;


import com.li.Result;
import com.li.ResultInfo;
import com.li.api.MenuService;
import com.li.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  根据角色ID得到菜单栏的接口
 * </p>
 *
 * @author kugga
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    public MenuService menuService;
    @RequestMapping("/getMenuList")
    public Result getMenuList(@RequestParam("roleName") String roleName){
        System.out.println(roleName);
        List<MenuDTO> menuDTOS=menuService.getMenuByRoleName(roleName);
        System.out.println(menuDTOS);
        return Result.success().CodeAndeMessage(ResultInfo.SUCCESS).data("menuList",menuDTOS);

    }

    @RequestMapping("/getMenuList2")
    public Result getMenuList2(){
        List<MenuDTO> menuDTOS=menuService.getMenuByRoleName("ADMIN");
        return Result.success().CodeAndeMessage(ResultInfo.SUCCESS).data("menuDTOS",menuDTOS);

    }

}

