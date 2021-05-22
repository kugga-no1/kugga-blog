package com.li.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.li.dto.UserDTO;
import com.li.pojo.User;
import com.li.mapper.UserMapper;
import com.li.api.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.vo.UserQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<String> getUserRoleByUserId(int id) {
        return this.baseMapper.getUserRoleByUserId(id);
    }

    public User getUserByUserName(String username) {
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("USERNAME",username);
        return this.baseMapper.selectOne(wrapper);
    }

    public Boolean checkPassWord(String username,String password){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("USERNAME", username);
        User user=this.baseMapper.selectOne(wrapper);
        if (user==null){
            return false;
        }
        String passwordindb=user.getPassword();  //在数据库中根据username查出来的密码
        return bCryptPasswordEncoder.matches(password,passwordindb);
    }

    @Override
    public List<UserDTO> queryUserByConditions(UserQueryVO userQueryVO) {
        return this.baseMapper.queryUserByConditions(userQueryVO);
    }

    @Override
    public int count(UserQueryVO userQueryVO) {
        return this.baseMapper.count(userQueryVO);
    }


}
