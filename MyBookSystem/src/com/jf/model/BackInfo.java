package com.jf.model;

import java.util.Date;

public class BackInfo {
    private Integer id;    //借阅编号
    private Integer fk_admin;//操作员
    private Date backDate;    //归还时间

    public BackInfo(Integer id) {
        this.id = id;
    }

    public BackInfo() {
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_admin() {
        return fk_admin;
    }

    public void setFk_admin(Integer fk_admin) {
        this.fk_admin = fk_admin;
    }
}
