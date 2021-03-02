package com.sni.web.controller;

import com.google.common.collect.Maps;
import com.sni.service.api.UserService;
import com.sni.service.constant.Constant;
import com.sni.service.domain.Result;
import com.sni.service.model.User;
import com.sni.web.annotation.OperationLog;
import com.sni.web.constant.OperationLogCons;
import com.sni.web.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    @OperationLog(operationModule = OperationLogCons.OperationModule.LOG_IN, operationType = OperationLogCons.OperationType.LOG_IN, operationDesc = "登录系统")
    public Result doLogin(@RequestBody User user) {
        try {
            if (userService.verifyAccount(user.getUsername(), user.getPassword())) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("username", user.getUsername());

                String jwtToken = new JwtUtil(secretKey).encode(user.getUsername(), Constant.Time.LOG_IN_EXPIRE_TIME, map);

                Map<String, String> tokenMap = Maps.newHashMap();
                tokenMap.put("jwtToken", jwtToken);
                return Result.success(tokenMap);
            } else {
                return Result.error("密码错误");
            }
        } catch (Exception e) {
            return Result.error();
        }
    }
}
