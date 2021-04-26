package com.cb.crm.settings.service;

import com.cb.crm.exception.LoginException;
import com.cb.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginact, String loginpwd, String ip) throws LoginException;

    List<User> getUserList();
}
