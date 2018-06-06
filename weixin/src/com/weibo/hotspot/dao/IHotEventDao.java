package com.weibo.hotspot.dao;

import java.util.List;

import com.weibo.hotspot.bean.HotEvent;
import com.weibo.hotspot.bean.HotEventAna;

/**
 * 概要说明 : 热点事件实体类Dao层接口.<br>
 * 详细说明 : <br>
 * 创建时间 : 2018-3-26, 下午3:01:16
 */
public interface IHotEventDao
{
    /**
     * 概要说明 : 保存热点事件及其与信息关系.<br>
     * 详细说明 : <br>
     * 
     * @param hotEventAna
     * @see com.weibo.hotspot.dao.IHotEventDao#save
     * @author zyy @ 2018-3-26, 下午3:04:14
     */
    void save(HotEventAna hotEventAna);
    
    /**
     * 概要说明 : 删除当天热点事件.<br>
     * 详细说明 : <br>
     * 
     * @param city
     * @param startTime 开始时间（格式 2018-03-27 00:00），不允许为空
     * @param endTime 结束时间（格式 2018-03-27 24:00），不允许为空
     * @return boolean
     * @see com.weibo.hotspot.dao.IHotEventDao#delete
     * @author zyy @ 2018-3-26, 下午9:07:48
     */
    boolean delete(String city, String startTime, String endTime);

    /**
     * 概要说明 : 根据地域和时间提取热点事件.<br>
     * 详细说明 : <br>
     * 
     * @param city 地域，不允许为空（连云港、上海、南京、北京、深圳）
     * @param startDate 开始时间，不允许为空
     * @param endDate 结束时间，允许为空，不为空时不允许早于startDate
     * @return List<HotEvent>
     * @see com.weibo.hotspot.dao.IHotEventDao#getHotEventsByCityAndTime
     * @author zyy @ 2018-3-26, 下午3:10:04
     */
    List<HotEvent> getHotEventsByCityAndTime(String city, String startDate, String endDate);
    
    /**
     * 概要说明 : 对象转换.<br>
     * 详细说明 : <br>
     * 
     * @param anaList
     * @return HotEvent
     * @see com.weibo.hotspot.dao.IHotEventDao#convertAnaToHotEvents
     * @author zyy @ 2018-3-26, 下午3:11:55
     */
    HotEvent convertAnaToHotEvents(HotEventAna hotEventAna);
}
