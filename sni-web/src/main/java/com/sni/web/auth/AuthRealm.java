package com.sni.web.auth;

import com.sni.service.api.UserService;
import com.sni.service.model.User;
import com.sni.web.utils.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthRealm extends AuthorizingRealm {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Autowired
    private UserService userService;

    /**
     * 标识这个Realm是专门用来验证JwtToken，不负责验证其他的token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        // 这个token是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }

        JwtUtil jwtUtil = new JwtUtil(secretKey);
        if(!jwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }

        // 判断username是否真实存在
        String username = (String) jwtUtil.decode(jwt).get("username");
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(jwt, jwt, "AuthRealm");
    }
}
