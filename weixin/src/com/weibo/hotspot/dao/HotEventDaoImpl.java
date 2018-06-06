/**
 * 
 */
package com.weibo.hotspot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.hotspot.bean.HotEvent;
import com.weibo.hotspot.bean.HotEventAna;
import com.weibo.hotspot.bean.HotEventWbinfoRelate;
import com.weibo.jg.bean.Wbinfor;

/**
 * 概要说明 : 热点事件实体类Dao层实现类.<br>
 */
@Repository("hotEventDao")
@Transactional
public class HotEventDaoImpl implements IHotEventDao
{
    @Autowired   
    private SessionFactory sessionFactory;

    @Override
    public void save(HotEventAna hotEventAna)
    {
        // 保存热点事件对象
        if (hotEventAna == null)
        {
            return;
        }
        HotEvent hotEvent = convertAnaToHotEvents(hotEventAna);
        sessionFactory.getCurrentSession().save(hotEvent);
        
        // 保存热点事件与微博信息关系
        List<Wbinfor> wbinfos = hotEventAna.getWbinfos();
        for (Wbinfor wbinfo : wbinfos)
        {
            HotEventWbinfoRelate relate = new HotEventWbinfoRelate();
            relate.setHotwid(hotEventAna.getHotwid());
            relate.setWid(wbinfo.getId());
            sessionFactory.getCurrentSession().save(relate);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HotEvent> getHotEventsByCityAndTime(String city, String startDate, String endDate)
    {
        if (StringUtils.isBlank(city) || StringUtils.isBlank(startDate))
        {
            return new ArrayList<HotEvent>();
        }
        
        StringBuffer hqlSb = new StringBuffer("from HotEvent where city = '");
        hqlSb.append(city).append("' and begintime > '").append(startDate).append("'");
        if (StringUtils.isNotBlank(endDate))
        {
            hqlSb.append(" and begintime < '").append(endDate).append("'");
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hqlSb.toString());
        List<HotEvent> hotEvents = query.list();
        if (hotEvents != null && hotEvents.size() > 0)
        {
            return hotEvents;
        }
        return new ArrayList<HotEvent>();
    }

    @Override
    public HotEvent convertAnaToHotEvents(HotEventAna hotEventAna)
    {
        if (hotEventAna == null)
        {
            return null;
        }
        
        HotEvent hotEvent = new HotEvent();
        hotEvent.setHotwid(hotEventAna.getHotwid());
        hotEvent.setCity(hotEventAna.getCity());
        hotEvent.setTopic(hotEventAna.getTopic());
        hotEvent.setKeyword(hotEventAna.getTopic());
        hotEvent.setScore(hotEventAna.getScore());
        hotEvent.setBegintime(hotEventAna.getStartTime());
        hotEvent.setDistrictcount(hotEventAna.getDistrictCount());
        hotEvent.setUseractivity(hotEventAna.getUserActivity());
        hotEvent.setUsercount(hotEvent.getUsercount());
        hotEvent.setWeiboCount(hotEventAna.getWbinfosCount());
        return hotEvent;
    }

    @Override
    public boolean delete(String city, String startTime, String endTime)
    {
        if (StringUtils.isBlank(city) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime))
        {
            return true;
        }
        
        StringBuffer hqlSb = new StringBuffer("delete from HotEvent where city = '");
        hqlSb.append(city).append("' and begintime >= '").append(startTime);
        hqlSb.append("' and begintime < '").append(endTime).append("'");
        Query query = sessionFactory.getCurrentSession().createQuery(hqlSb.toString());
        query.executeUpdate();
        return true;
    }

}
