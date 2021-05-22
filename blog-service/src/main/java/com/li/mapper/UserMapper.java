package com.li.mapper;

import com.li.dto.UserDTO;
import com.li.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.vo.UserQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

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
public interface UserMapper extends BaseMapper<User> {
    List<String> getUserRoleByUserId(@Param("id") int id);
    List<UserDTO> queryUserByConditions(@Param("userQueryVO") UserQueryVO userQueryVO);
    int count(@Param("userQueryVO")UserQueryVO userQueryVO);
}
