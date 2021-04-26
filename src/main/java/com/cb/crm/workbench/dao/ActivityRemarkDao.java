package com.cb.crm.workbench.dao;

import com.cb.crm.workbench.domian.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int NumofActReById(String[] ids);

    int deleteActReById(String[] ids);

    List<ActivityRemark> getActMarklistById(String id);

    int RemoveRemark(String id);

    int SaveRemark(ActivityRemark activityRemark);

    ActivityRemark getnoteContentById(String id);

    int updateRemark(ActivityRemark activityRemark);
}
