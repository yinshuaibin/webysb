package com.ysb.controller;

import com.ysb.bean.User;
import com.ysb.controller.base.BaseController;
import com.ysb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yinshuaibin
 * @date 2021/3/22 10:25
 * @description
 */
@RestController
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public int createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
