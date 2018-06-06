package com.weibo.hotspot.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotevent_status_relate", schema = "")
@SuppressWarnings("serial")
public class HotEventWbinfoRelate implements Serializable
{
    @Id  
    @GeneratedValue(strategy =GenerationType.IDENTITY)   
    @Column(name = "id", nullable = false)  
    private int id;    //ID
    
    @Column(name = "hotwid", nullable = true)  
    private String hotwid;    //热点事件编号
    

    @Column(name = "wid", nullable = true)  
    private String wid;    //微博信息ID


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getHotwid()
    {
        return hotwid;
    }


    public void setHotwid(String hotwid)
    {
        this.hotwid = hotwid;
    }


    public String getWid()
    {
        return wid;
    }


    public void setWid(String wid)
    {
        this.wid = wid;
    }
    
}
