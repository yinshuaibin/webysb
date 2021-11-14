package com.ysb.mapper;

import com.ysb.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author yinshuaibin
 * @date 2021/3/23 10:26
 * @description
 */
public interface UserMapper {

    /**
     * 创建用户
     * @param user 用户
     */
    @Insert("insert into tb_user (username,password) value (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(User user);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    @Select("select id, username, password from tb_user where username = #{username}")
    User findUserByUserName(String username);
}
