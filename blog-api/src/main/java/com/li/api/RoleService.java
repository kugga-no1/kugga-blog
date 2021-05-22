package com.li.api;

import com.li.dto.RoleDTO;
import com.li.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kugga
 * @since 2021-05-13
 */
public interface RoleService extends IService<Role> {
    List<RoleDTO> getRole();
}
