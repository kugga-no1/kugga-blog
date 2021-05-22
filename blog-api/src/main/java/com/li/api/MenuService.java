package com.li.api;

import com.li.dto.MenuDTO;
import com.li.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kugga
 * @since 2021-05-15
 */
public interface MenuService extends IService<Menu> {

    List<MenuDTO> getMenuByRoleName(String roleName);
}
