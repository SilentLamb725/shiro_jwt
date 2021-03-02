package com.sni.service;

import com.sni.service.utils.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenTest {

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void createTokenTest() {
        System.out.println(tokenUtil.createToken(1));
    }

    @Test
    public void checkTokenTest() {
        System.out.println(tokenUtil.checkToken("1_342d739840154569ba7229a81cfcc2cd"));
    }

    @Test
    public void clearTokenTest() {
        System.out.println(tokenUtil.clearToken("1_342d739840154569ba7229a81cfcc2cd"));
    }
}
