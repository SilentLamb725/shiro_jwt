package com.sni.web.auth;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义一个Filter，用来拦截所有请求判断是否携带token
 * isAccessAllowed：判断是否携带了有效的token
 * onAccessDenied：没有携带token时进行账号密码登录，登录成功则允许访问，登录失败则拒绝访问
 */
public class JwtFilter extends AccessControlFilter {

    /**
     * 返回true，shiro则允许访问url
     * 返回false，shiro会根据onAccessDenied的方法返回值决定是否允许访问url
     * @params
     * @return
     * @date 2021/3/1 15:08
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        // 始终返回false来使用onAccessDenied方法
        return false;
    }

    /**
     * 返回true则表明登录通过
     * @params
     * @return
     * @date 2021/3/1 15:09
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 和前端约定将token放在请求头Header中
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorization = request.getHeader("Authorization");
        JwtToken jwtToken = new JwtToken(authorization);

        try {
            // 委托 realm 进行登录认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(servletResponse);
            return false;
        }
        return true;
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write("login error");
    }
}
