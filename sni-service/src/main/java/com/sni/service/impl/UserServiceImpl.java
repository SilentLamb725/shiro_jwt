package com.sni.service.impl;

import com.sni.service.api.UserService;
import com.sni.service.constant.Constant;
import com.sni.service.dao.UserMapper;
import com.sni.service.model.User;
import com.sni.service.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public int signUpAccount(User user) {

        LOGGER.info("enter signUpAccount, user:{}", user.toString());

        // 参数错误
        if (StringUtils.isEmpty(user.getUsername())
                  || StringUtils.isEmpty(user.getPassword())) {

            LOGGER.info("exit signUpAccount, code:{}", Constant.SignUpCode.ARGS_ERROR);

            return Constant.SignUpCode.ARGS_ERROR;
        }

        // 用户名已存在
        User userDb = userMapper.selectByAccount(user.getUsername());
        if (null != userDb) {

            LOGGER.info("exit signUpAccount, code:{}", Constant.SignUpCode.EXIST_ACCOUNT);

            return Constant.SignUpCode.EXIST_ACCOUNT;
        }

        String encryptPwd = MD5Util.saltEncrypt(user.getPassword());
        user.setPassword(encryptPwd);

        user.setCreateTime(new Date());
        user.setLastLogInTime(new Date());
        user.setIsDisabled(Constant.Status.NOT_DISABLED);

        userMapper.insertSelective(user);

        LOGGER.info("exit signUpAccount, code:{}", Constant.SignUpCode.SUCCESS);
        return Constant.SignUpCode.SUCCESS;
    }

    /**
     * 验证账号密码
     *
     * @param account
     * @param pwd
     * @return
     */
    public boolean verifyAccount(String account, String pwd) {
        User user = userMapper.selectByAccount(account);

        if (null != user) {
            return MD5Util.verify(pwd, user.getPassword());
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByAccount(username);
    }
}
