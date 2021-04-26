package com.cb.crm.workbench.web.controller;

import com.cb.crm.settings.domain.User;
import com.cb.crm.utils.DateTimeUtil;
import com.cb.crm.utils.PrintJson;
import com.cb.crm.utils.ServiceFactory;
import com.cb.crm.utils.UUIDUtil;
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
        if ("/workbench/clue/getUserlist.do".equals(path)) {
            getUserlist(request, response);
        } else if ("/workbench/clue/SaveClue.do".equals(path)) {
            SaveClue(request, response);
        }
    }

    private void SaveClue(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");








    }

    private void getUserlist(HttpServletRequest request, HttpServletResponse response) {
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        List<User> userList = clueService.getUserlist();
        PrintJson.printJsonObj(response,userList);
    }
}
