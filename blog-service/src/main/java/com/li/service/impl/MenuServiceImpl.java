package com.li.service.impl;

import com.li.dto.MenuDTO;
import com.li.pojo.Menu;
import com.li.mapper.MenuMapper;
import com.li.api.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kugga
 * @since 2021-05-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<MenuDTO> getMenuByRoleName(String roleName) {
    List<Menu> menus=this.baseMapper.getMenuByRoleName(roleName);
    List<MenuDTO> menuDTOS=new ArrayList<>();
    for(Menu menu:menus){
        MenuDTO menuDTO=new MenuDTO();
        menuDTO.addMenu(menu);
        if(menu.getParentId()==0){   //如果是顶级菜单 直接加入list 把它的次级加进去
            menuDTO.addMenu(menu);
            addMenuChildren(menuDTO,menus);   //处理child
            menuDTOS.add(menuDTO);
        }
    }
    return menuDTOS;
    }

    public void addMenuChildren(MenuDTO menuDTO,List<Menu> menus){
        for(Menu menu:menus){
            if(menu.getParentId()==menuDTO.getId()){
                MenuDTO menuDTOChildren=new MenuDTO();
                menuDTOChildren.addMenu(menu);                  //如果找到有menu的parentid是menuDto 就把它放入到menuDto的children里
                addMenuChildren(menuDTOChildren,menus);         //遍历 把子树的子树也插进去
                menuDTO.getMenuChildrens().add(menuDTOChildren);
            }
        }

    }
}
