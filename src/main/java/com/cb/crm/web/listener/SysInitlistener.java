package com.cb.crm.web.listener;

import com.cb.crm.settings.domain.DicValue;
import com.cb.crm.settings.service.DicService;
import com.cb.crm.settings.service.Impl.DicServiceImpl;
import com.cb.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitlistener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("上下文域对象创建了");
        ServletContext application = event.getServletContext();
        DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());
        Map<String,List<DicValue>> map = dicService.getDic();
        Set<String> set =map.keySet();
        for(String dictype:set){
            List<DicValue> valueList = (List<DicValue>) map.get(dictype);
            application.setAttribute(dictype,valueList);
        }
        System.out.println("获取字典结束");

    }
}
