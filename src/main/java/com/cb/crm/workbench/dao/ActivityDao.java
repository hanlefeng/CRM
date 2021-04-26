package com.cb.crm.workbench.dao;


import com.cb.crm.workbench.domian.Activity;

import java.util.List;
import java.util.Map;


public interface ActivityDao {

    int saveActivity(Activity activity);

    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getactivityListBycondition(Map<String, Object> map);

    int deleteActById(String[] ids);

    Activity getActivityById(String id);

    int updateActivity(Activity activity);

    Activity getActivityDetailById(String id);
}
