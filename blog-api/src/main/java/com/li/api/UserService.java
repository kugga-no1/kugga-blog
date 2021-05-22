package com.li.api;

import com.li.dto.UserDTO;
import com.li.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.vo.UserQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kugga
 * @since 2021-05-13
 */
public interface UserService extends IService<User> {

    List<String> getUserRoleByUserId(int id);
    User getUserByUserName(String username);
    Boolean checkPassWord(String username,String password);
    List<UserDTO> queryUserByConditions(UserQueryVO userQueryVO);
    int count(UserQueryVO userQueryVO);
}
