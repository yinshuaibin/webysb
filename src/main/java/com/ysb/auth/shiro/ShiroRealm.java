package com.ysb.auth.shiro;

import com.ysb.auth.jwt.JWTToken;
import com.ysb.auth.jwt.JWTUtil;
import com.ysb.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author yinshuaibin
 * @date 2020/4/28 17:01
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        log.info("用户: {} 进行了验权", username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 实际并未使用权限管理
        return authorizationInfo;
    }

    /**
     * 使用JWT替代原生Token
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        if ("1" != null) {
            if (!JWTUtil.verify(token, username, "1")) {
                throw new BusinessException("密码校验失败");
            }
            return new SimpleAuthenticationInfo("1", token, "realm");
        } else {
            throw new BusinessException("用户不存在");
        }
    }
}

