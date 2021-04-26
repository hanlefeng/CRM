package com.cb.crm.workbench.service;

import com.cb.crm.vo.pagelistVO;
import com.cb.crm.workbench.domian.Activity;
import com.cb.crm.workbench.domian.ActivityRemark;

import java.util.List;
import java.util.Map;

    public interface ActivityService {

    int saveActivity(Activity activity);

    pagelistVO<Activity> pagelist(Map<String, Object> map);

    Boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    Boolean updateActivity(Activity activity);

    Activity getdetail(String id);

    List<ActivityRemark> getActMarklistByid(String id);

    Boolean RemoveRemark(String id);

    Boolean SaveRemark(ActivityRemark activityRemark);

        ActivityRemark getnoteContentById(String id);

        Boolean updateRemark(ActivityRemark activityRemark);
    }
