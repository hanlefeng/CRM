package com.cb.crm.workbench.web.controller;

import com.cb.crm.settings.domain.User;
import com.cb.crm.settings.service.Impl.UserServiceImpl;
import com.cb.crm.settings.service.UserService;
import com.cb.crm.utils.DateTimeUtil;
import com.cb.crm.utils.PrintJson;
import com.cb.crm.utils.ServiceFactory;
import com.cb.crm.utils.UUIDUtil;
import com.cb.crm.vo.pagelistVO;
import com.cb.crm.workbench.domian.Activity;
import com.cb.crm.workbench.domian.ActivityRemark;
import com.cb.crm.workbench.service.ActivityService;
import com.cb.crm.workbench.service.Impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/activity/saveActivity.do".equals(path)){
            saveActivity(request,response);
        }else if("/workbench/activity/pagelist.do".equals(path)){
            pagelist(request,response);
        }
        else if("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }else if ("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request,response);
        }
        else if ("/workbench/activity/updateActivity.do".equals(path)){
            updateActivity(request,response);
        }
        else if ("/workbench/activity/detail.do".equals(path)){
            detail(request,response);
        }
        else if ("/workbench/activity/showActMarklist.do".equals(path)){
            showActMarklist(request,response);
        }
        else if ("/workbench/activity/RemoveRemark.do".equals(path)){
            RemoveRemark(request,response);
        }
        else if ("/workbench/activity/SaveRemark.do".equals(path)){
            SaveRemark(request,response);
        }
        else if ("/workbench/activity/showeditmodal.do".equals(path)){
            showeditmodal(request,response);
        }
        else if ("/workbench/activity/updateRemark.do".equals(path)){
            updateRemark(request,response);
        }
    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String noteContent = request.getParameter("noteContent");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(id);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditTime(editTime);
        activityRemark.setEditBy(editBy);
        activityRemark.setEditFlag(editFlag);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag = activityService.updateRemark(activityRemark);
        PrintJson.printJsonFlag(response,flag);
    }

    private void showeditmodal(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        ActivityRemark activityRemark = activityService.getnoteContentById(id);
        PrintJson.printJsonObj(response,activityRemark);
    }

    private void SaveRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String activityId = request.getParameter("activityId");
        String noteContent = request.getParameter("noteContent");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(id);
        activityRemark.setActivityId(activityId);
        activityRemark.setCreateBy(createBy);
        activityRemark.setCreateTime(createTime);
        activityRemark.setEditFlag(editFlag);
        activityRemark.setNoteContent(noteContent);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag = activityService.SaveRemark(activityRemark);
        PrintJson.printJsonFlag(response,flag);

    }

    private void RemoveRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag = activityService.RemoveRemark(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void showActMarklist(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> activityRemarkList = activityService.getActMarklistByid(id);
        PrintJson.printJsonObj(response,activityRemarkList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到了");
        String id = request.getParameter("id");
        System.out.println("获取到的id值为"+id);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity activity = activityService.getdetail(id);
        request.setAttribute("activity",activity);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }

    private void updateActivity(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setEditTime(editTime);
        activity.setEditBy(editBy);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag = activityService.updateActivity(activity);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UserService act = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = act.getUserList();
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Map<String,Object> map = activityService.getUserListAndActivity(id);
        PrintJson.printJsonObj(response,map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag = activityService.delete(ids);
        System.out.println(flag);
        PrintJson.printJsonFlag(response,flag);
    }

    private void pagelist(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pagenostr = request.getParameter("pageno");
        String pagesizestr = request.getParameter("pagesize");
        Integer pageno = Integer.valueOf(pagenostr);
        Integer pagesize = Integer.valueOf(pagesizestr);
        Integer pagelueguo = (pageno-1)*pagesize;
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pagelueguo",pagelueguo);
        map.put("pagesize",pagesize);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        pagelistVO<Activity> vo = activityService.pagelist(map);
        PrintJson.printJsonObj(response,vo);
    }

    private void saveActivity(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        int count = activityService.saveActivity(activity);
        if(count==1){
            PrintJson.printJsonFlag(response,true);
        }else {
            PrintJson.printJsonFlag(response,false);
        }

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService act = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = act.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
