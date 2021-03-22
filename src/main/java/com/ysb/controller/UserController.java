package com.ysb.controller;

import com.ysb.bean.User;
import com.ysb.controller.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2021/3/22 10:25
 * @description
 */
@RestController
public class UserController extends BaseController {

    @PostMapping("/createUser")
    public User createUser(@RequestBody Map map){
        System.out.println(map);
        return new User();
    }

}
