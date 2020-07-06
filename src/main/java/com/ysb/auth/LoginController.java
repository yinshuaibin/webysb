package com.ysb.auth;

import com.ysb.auth.jwt.JWTUtil;
import com.ysb.exception.BusinessException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> loginCheck(@RequestBody Map<String, String> loginUser, HttpServletRequest request) {
        String username = loginUser.get("username");
        String password = loginUser.get("password");
        if ("yinshuaibin".equals(username)){
            throw new BusinessException("该用户不存在或已经过期,请换个用户登录");
        }
        if ("root".equals(username) || "1".equals(password)){
            return new HashMap<String,String>(){{put("jnumber", JWTUtil.sign(username, "1"));}};
        }
        throw new BusinessException("密码错误");
	}

	@RequestMapping(value = "/removeUser")
    public void removeUser(){
        SecurityUtils.getSubject().logout();
    }

}
