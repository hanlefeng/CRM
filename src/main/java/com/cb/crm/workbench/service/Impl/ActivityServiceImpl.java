package com.cb.crm.workbench.service.Impl;

import com.cb.crm.settings.dao.UserDao;
import com.cb.crm.settings.domain.User;
import com.cb.crm.utils.SqlSessionUtil;
import com.cb.crm.vo.pagelistVO;
import com.cb.crm.workbench.dao.ActivityDao;
import com.cb.crm.workbench.dao.ActivityRemarkDao;
import com.cb.crm.workbench.domian.Activity;
import com.cb.crm.workbench.domian.ActivityRemark;
import com.cb.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    @Override
    public int saveActivity(Activity activity) {
        int count = activityDao.saveActivity(activity);
        return count;
    }
    @Override
    public pagelistVO<Activity> pagelist(Map<String, Object> map) {
        int total = activityDao.getTotalByCondition(map);
        List<Activity> activityList = activityDao.getactivityListBycondition(map);
        pagelistVO<Activity> vo = new pagelistVO<>();
        vo.setTotal(total);
        vo.setPagelist(activityList);
        return vo;
    }

    @Override
    public Boolean delete(String[] ids) {
        Boolean flag = true;
        //活动备注表中需要删除的条数
        int count1 = activityRemarkDao.NumofActReById(ids);
        System.out.println("需要删除的条数"+count1);
        //活动备注表中删除的条数
        int count2 = activityRemarkDao.deleteActReById(ids);
        System.out.println("删除的条数为;"+count2);
        if(count1!=count2){
            flag = false;
        }
        System.out.println("一和二比较的结果为"+flag);
        //活动表中删除的条数
        int count3 = activityDao.deleteActById(ids);
        if(count3!=ids.length){
            flag = false;
        }
        System.out.println(flag);
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        List<User> userList = userDao.getUserList();
        Activity activity = activityDao.getActivityById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("userlist",userList);
        map.put("activity",activity);
        return map;
    }

    @Override
    public Boolean updateActivity(Activity activity) {
        int count = activityDao.updateActivity(activity);
        System.out.println("修改的记录条数为"+count);
        if (count==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Activity getdetail(String id) {
        Activity activity = activityDao.getActivityDetailById(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> getActMarklistByid(String id) {
        List<ActivityRemark> activityRemarkList = activityRemarkDao.getActMarklistById(id);
        return activityRemarkList;
    }

    @Override
    public Boolean RemoveRemark(String id) {
        Boolean flag = false;
        int count = activityRemarkDao.RemoveRemark(id);
        if (count!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean SaveRemark(ActivityRemark activityRemark) {
        Boolean flag = false;
        int count = activityRemarkDao.SaveRemark(activityRemark);
        if (count!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public ActivityRemark getnoteContentById(String id) {
        ActivityRemark activityRemark = activityRemarkDao.getnoteContentById(id);
        return activityRemark;
    }

    @Override
    public Boolean updateRemark(ActivityRemark activityRemark) {
        Boolean flag = false;
        int count = activityRemarkDao.updateRemark(activityRemark);
        if (count!=0){
            flag=true;
        }
        return flag;
    }
}
