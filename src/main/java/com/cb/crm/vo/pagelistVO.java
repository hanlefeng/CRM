package com.cb.crm.vo;

import java.util.List;

public class pagelistVO<T> {
    private int Total;
    private List<T> pagelist;

    public pagelistVO() {
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public List<T> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<T> pagelist) {
        this.pagelist = pagelist;
    }
}
