package com.cb.crm.settings.service.Impl;

import com.cb.crm.settings.dao.DicTypeDao;
import com.cb.crm.settings.dao.DicValueDao;
import com.cb.crm.settings.domain.DicType;
import com.cb.crm.settings.domain.DicValue;
import com.cb.crm.settings.service.DicService;
import com.cb.crm.utils.SqlSessionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getDic() {
        List<DicType> typelist = dicTypeDao.getDicType();
        Map<String,List<DicValue>> map = new HashMap<>();
        for (DicType dicType:typelist){
            String typeCode = dicType.getCode();
            List<DicValue> valuelist = dicValueDao.getDicValue(typeCode);
            map.put(typeCode,valuelist);
        }
        return map;
    }
}
