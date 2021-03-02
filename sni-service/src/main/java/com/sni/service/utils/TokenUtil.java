package com.sni.service.utils;

import com.sni.service.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Component
public class TokenUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据userId生成令牌
     * @param userId
     * @return
     */
    public String createToken(int userId) {

        // 生成token
        UUID uuid = UUID.randomUUID();
        String token = userId + "_" + uuid.toString().replace("-", "");

        // 将token存入redis
        String key = userId + "_token";
        redisUtil.set(key, token, Constant.Num.ONE * 3600);

        return token;
    }

    /**
     * 校验令牌
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) return false;
        if (token.split("_").length != 2) return false;

        String key = token.split("_")[0] + "_token";
        String rToken = (String) redisUtil.get(key);

        if (StringUtils.isEmpty(rToken)) return false;
        if (!token.equals(rToken)) return false;

        redisUtil.set(key, rToken, Constant.Num.ONE * 3600);

        return true;
    }

    /**
     * 清除令牌
     * @param token
     */
    public boolean clearToken(String token) {
        if (StringUtils.isEmpty(token)) return false;
        if (StringUtils.isEmpty(token.split("_").length != 2)) return false;

        String key = token.split("_")[0] + "_token";
        String rToken = (String) redisUtil.get(key);

        if (StringUtils.isEmpty(rToken)) return false;
        if (!token.equals(rToken)) return false;

        redisUtil.del(key);

        return true;
    }
}
