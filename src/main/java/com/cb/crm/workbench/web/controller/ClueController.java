package com.cb.crm.workbench.web.controller;

import com.cb.crm.settings.domain.User;
import com.cb.crm.utils.PrintJson;
import com.cb.crm.utils.ServiceFactory;
import com.cb.crm.workbench.service.ClueService;
import com.cb.crm.workbench.service.Impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClueController extends HttpServlet{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/clue/getUserlist.do".equals(path)){
            getUserlist(request,response);
        }
    }

    private void getUserlist(HttpServletRequest request, HttpServletResponse response) {
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        List<User> userList = clueService.getUserlist();
        PrintJson.printJsonObj(response,userList);
    }
}
