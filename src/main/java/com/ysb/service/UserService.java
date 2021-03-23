package com.ysb.service;

import com.ysb.bean.User;

/**
 * @author yinshuaibin
 * @date 2021/3/23 10:22
 * @description
 */
public interface UserService {

    /**
     * 创建用户
     * @param user 用户
     * @return 1
     */
    int createUser(User user);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户信息
     */
    User findUserByUserName(String username);

}
