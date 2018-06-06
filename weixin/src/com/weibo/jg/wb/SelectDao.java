package com.weibo.jg.wb;

import java.util.List;

import com.weibo.jg.bean.Wbinfor;

public interface SelectDao
{
    /**
     * 概要说明 : 查询某一时间段的微博信息.<br>
     * 详细说明 : <br>
     * 
     * @param startTime
     * @param endTime
     * @return List<Wbinfor>
     * @see com.weibo.jg.wb.SelectDao#getWbinfosByTime
     * @author zyy @ 2018-3-26, 下午6:45:54
     */
    public List<Wbinfor> getWbinfosByTime(String startTime, String endTime);
    
    /**
     * 概要说明 : 查询某地某日的所有信息.<br>
     * 详细说明 : <br>
     * 
     * @param city 地域名称
     * @param startTime 开始时间（格式 2018-03-27 00:00），不允许为空
     * @param endTime 结束时间（格式 2018-03-27 24:00），不允许为空 
     * @return List<Wbinfor>
     * @see com.weibo.jg.wb.SelectDao#getWbinforsByCityAndTime
     * @author zyy @ 2018-3-26, 上午10:41:39
     */
    public List<Wbinfor> getWbinforsByCityAndTime(String city, String startTime, String endTime);
    
    /**
     * 概要说明 : 根据热点事件获取对应微博信息.<br>
     * 详细说明 : <br>
     * 
     * @param hotwid
     * @return List<Wbinfor>
     * @see com.weibo.jg.wb.SelectDao#getWbinfosByHotEvent
     * @author zyy @ 2018-3-26, 下午4:36:44
     */
    public List<Wbinfor> getWbinfosByHotEvent(String hotwid);
    
    //保存微博信息
    void save(Wbinfor wbi);
    //获取时间范围内的微博地点数量
    public List<Object> selectPOIcount(String start, String end,String city);
    //根据地址查询
    Wbinfor getWbinforByUrl(String url);
    
}
