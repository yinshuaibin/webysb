package com.ysb.service.impl;

import com.ysb.bean.User;
import com.ysb.mapper.UserMapper;
import com.ysb.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yinshuaibin
 * @date 2021/3/23 10:22
 * @description
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.createUser(user);
        System.out.println(user.getId());
        return 1;
    }

    @Cacheable(cacheNames= "user", key="#p0",  unless="#result == null")
    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }
}
