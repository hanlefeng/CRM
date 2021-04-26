package com.cb.crm.settings.web.controller;

import com.cb.crm.settings.domain.User;
import com.cb.crm.settings.service.Impl.UserServiceImpl;
import com.cb.crm.settings.service.UserService;
import com.cb.crm.utils.MD5Util;
import com.cb.crm.utils.PrintJson;
import com.cb.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            System.out.println(1);
            logins(request,response);
            System.out.println(1);
        }
    }
    private void logins(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(1);
        String loginact = request.getParameter("loginAct");
        String loginpwd = request.getParameter("loginPwd");
        loginpwd = MD5Util.getMD5(loginpwd);
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            User user = us.login(loginact,loginpwd,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
