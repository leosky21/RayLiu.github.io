package com.weibo.hotspot.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * 概要说明 : 热点事件实体类.<br>
 */
@Entity
@Table(name = "hotevent", schema = "")
@SuppressWarnings("serial")
public class HotEvent implements Serializable
{
    @Id  
    @GeneratedValue(strategy =GenerationType.IDENTITY)   
    @Column(name = "id", nullable = false)  
    private int id;    //事件编号
    
    @Column(name = "hotwid", nullable = true)  
    private String hotwid;    //热点微博ID号
    
    @Column(name = "city", nullable = true)
    private String city;    //热点事件的所在地域
    
    @Column(name = "topic", nullable = true)  
    private String topic;//事件话题

    @Column(name = "keyword", nullable = true)  
    private String keyword;//话题关键词
    
    @Column(name = "score", nullable = true)  
    private double score;  //热度值
    
    @Column(name = "begintime", nullable = true)  
    private String begintime;   //最早发布时间
    
    @Column(name = "weiboCount", nullable = true)  
    private int weiboCount;//参与微博数
    
    @Column(name = "districtcount", nullable = true)  
    private int districtcount ;//地域分布
    
    @Column(name = "useractivity", nullable = true)  
    private int useractivity; //用户活跃度
    
    @Column(name = "usercount", nullable = true)  
    private int usercount;//参与用户数

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
    
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public String getBegintime()
    {
        return begintime;
    }

    public void setBegintime(String begintime)
    {
        this.begintime = begintime;
    }

    public int getDistrictcount()
    {
        return districtcount;
    }

    public void setDistrictcount(int districtcount)
    {
        this.districtcount = districtcount;
    }

    public int getUseractivity()
    {
        return useractivity;
    }

    public void setUseractivity(int useractivity)
    {
        this.useractivity = useractivity;
    }

    public int getUsercount()
    {
        return usercount;
    }

    public void setUsercount(int usercount)
    {
        this.usercount = usercount;
    }

    public int getWeiboCount()
    {
        return weiboCount;
    }

    public void setWeiboCount(int weiboCount)
    {
        this.weiboCount = weiboCount;
    }
    
}
