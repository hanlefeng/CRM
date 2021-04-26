package com.cb.crm.settings.dao;

import com.cb.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getDicValue(String typeCode);
}
