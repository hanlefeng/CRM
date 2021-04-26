package com.cb.crm.settings.service.Impl;

import com.cb.crm.exception.LoginException;
import com.cb.crm.settings.dao.UserDao;
import com.cb.crm.settings.domain.User;
import com.cb.crm.settings.service.UserService;
import com.cb.crm.utils.DateTimeUtil;
import com.cb.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private  UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginact, String loginpwd, String ip) throws LoginException {
        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginact);
        map.put("loginPwd",loginpwd);
        User user = userDao.login(map);
        System.out.println(user);
        if(user==null){
            throw new LoginException("账号密码错误");
        }
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){
            throw new LoginException("该账号已过期");
        }
        String lockState = user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("该账号已被封");
        }
        String allowIps = user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw new LoginException("该ip地址不允许被访问");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }
}
