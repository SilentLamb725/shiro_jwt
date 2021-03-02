package com.sni.service;

import com.sni.service.api.UserService;
import com.sni.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountTest {

    @Resource
    private UserService userService;

    @Test
    public void verifyAccount() {
        String name = "gaoyang";
        String pwd = "123455";

        System.out.println(userService.verifyAccount(name, pwd));
    }

    @Test
    public void signUpTest() {
        String name = "gaoyang1";
        String password = "123455";

        User user = new User();
        user.setUsername(name);
        user.setPassword(password);

        int resultCode = userService.signUpAccount(user);

        System.out.println(resultCode);
    }
}
