package com.sni.web.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private String jwtToken;

    public JwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    /**
     * 类似用户名
     * @return
     */
    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    /**
     * 类似密码
     * @return
     */
    @Override
    public Object getCredentials() {
        return jwtToken;
    }
}
