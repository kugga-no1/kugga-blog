package com.li.service.impl;

import com.li.dto.RoleDTO;
import com.li.pojo.Role;
import com.li.mapper.RoleMapper;
import com.li.api.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kugga
 * @since 2021-05-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<RoleDTO> getRole() {
        return this.baseMapper.getRole();
    }
}
