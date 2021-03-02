package com.sni.service.api;

import com.sni.service.model.User;

import java.util.Optional;

public interface UserService {

    int signUpAccount(User user);

    boolean verifyAccount(String account, String pwd);

    User findByUsername(String username);
}
