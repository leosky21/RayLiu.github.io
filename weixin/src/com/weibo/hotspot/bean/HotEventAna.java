package com.weibo.hotspot.bean;

import java.util.ArrayList;
import java.util.List;

import com.weibo.jg.bean.Wbinfor;

/**
 * 概要说明 : 热点事件对象-分析是使用.<br>
 */
public class HotEventAna
{

    private String hotwid; // 事件编号
    
    private List<Wbinfor> wbinfos; // 事件列表
    
    private String startTime; // 最早发布时间

    private String topic;// 事件话题

    private double score; // 热度值
    
    private String city;//地域

    private int districtCount;// 地域分布

    private int UserActivity; // 用户活跃度

    private int userCount;// 参与用户数

    List<String> words = new ArrayList<String>();

    public List<String> getWords()
    {
        return words;
    }

    public void setWords(List<String> words)
    {
        this.words = words;
    }

    public int getDistrictCount()
    {
        return districtCount;
    }

    public void setDistrictCount(int districtCount)
    {
        this.districtCount = districtCount;
    }

    public int getWbinfosCount()
    {
        return wbinfos.size();
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

    public List<Wbinfor> getWbinfos()
    {
        return wbinfos;
    }

    public void setWbinfos(List<Wbinfor> wbinfos)
    {
        this.wbinfos = wbinfos;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public int getUserActivity()
    {
        return UserActivity;
    }

    public void setUserActivity(int userActivity)
    {
        UserActivity = userActivity;
    }

    public int getUserCount()
    {
        return userCount;
    }

    public void setUserCount(int userCount)
    {
        this.userCount = userCount;
    }

}
