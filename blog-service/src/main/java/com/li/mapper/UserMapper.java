package com.li.mapper;

import com.li.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

}
