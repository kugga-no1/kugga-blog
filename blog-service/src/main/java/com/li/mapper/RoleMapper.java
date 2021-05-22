package com.li.mapper;

import com.li.dto.MenuDTO;
import com.li.dto.RoleDTO;
import com.li.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kugga
 * @since 2021-05-13
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<MenuDTO> getMenuByRoleName();
    List<RoleDTO> getRole();
}
