/**  

/**  
 * 金鸽公司源代码，版权归金鸽公司所有。<br>
 * 项目名称 : weixin
 */

package com.weibo.jg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 概要说明 : 微博信息 <br>
 * 详细说明 : <br>
 * 创建时间 : 2018年3月20日 下午2:08:21 <br>
 * 
 * @author by lj
 */
@Entity
@Table(name = "wbinfor", schema = "")
public class Wbinfor
{
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "uid", nullable = true)
    private String uid;// 微博号
    @Column(name = "city", nullable = true)
    private String city;// 城市
    @Column(name = "infortext", nullable = true)
    private String infortext;// '微博信息',
    @Column(name = "url", nullable = true)
    private String url;// '微博地址',
    @Column(name = "publicTime", nullable = true)
    private String publicTime;// '发布时间',
    @Column(name = "place", nullable = true)
    private String place;// '微博发布位置',
    @Column(name = "lon", nullable = true)
    private String lon;// '经度值',
    @Column(name = "lat", nullable = true)
    private String lat;// '纬度值',
    @Column(name = "reNum", nullable = false)
    private int reNum = 0;// 转发数,
    @Column(name = "coNum", nullable = false)
    private int coNum = 0;// 评论数,
    @Column(name = "liNum", nullable = false)
    private int liNum = 0;// 点赞数,

    public Wbinfor(){}
    public Wbinfor(Wbinfor wbinfor)
    {
        id = wbinfor.getId();
        uid = wbinfor.getUid();
        city = wbinfor.getCity();
        infortext = wbinfor.getInfortext();
        url = wbinfor.getUrl();
        publicTime = wbinfor.getPublicTime();
        place = wbinfor.getPlace();
        lon = wbinfor.getLon();
        lat = wbinfor.getLat();
        reNum = wbinfor.getReNum();
        coNum = wbinfor.getCoNum();
        liNum = wbinfor.getLiNum();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getInfortext()
    {
        return infortext;
    }

    public void setInfortext(String infortext)
    {
        this.infortext = infortext;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPublicTime()
    {
        return publicTime;
    }

    public void setPublicTime(String publicTime)
    {
        this.publicTime = publicTime;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getLon()
    {
        return lon;
    }

    public void setLon(String lon)
    {
        this.lon = lon;
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public int getReNum()
    {
        return reNum;
    }

    public void setReNum(int reNum)
    {
        this.reNum = reNum;
    }

    public int getCoNum()
    {
        return coNum;
    }

    public void setCoNum(int coNum)
    {
        this.coNum = coNum;
    }

    public int getLiNum()
    {
        return liNum;
    }

    public void setLiNum(int liNum)
    {
        this.liNum = liNum;
    }

}
