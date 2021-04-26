package com.cb.crm.workbench.service.Impl;

import com.cb.crm.settings.dao.UserDao;
import com.cb.crm.settings.domain.User;
import com.cb.crm.utils.SqlSessionUtil;
import com.cb.crm.workbench.dao.ClueDao;
import com.cb.crm.workbench.service.ClueService;

import java.util.List;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public List<User> getUserlist() {
        List<User> userList = userDao.getUserList();
        return userList;
    }
}
