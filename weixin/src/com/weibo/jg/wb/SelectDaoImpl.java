package com.weibo.jg.wb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.jg.bean.Wbinfor;
import com.weixin.xm.utils.LogUtil;

@Repository("selectDao")
@Transactional
public class SelectDaoImpl implements SelectDao
{
    @Autowired   
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Wbinfor> getWbinforsByCityAndTime(String city, String startTime, String endTime)
    {
        if (StringUtils.isBlank(city) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime))
        {
            return null;
        }
        
        StringBuffer sqlSb = new StringBuffer("select * from wbinfor where city = '");
        sqlSb.append(city).append("' and publicTime >= '").append(startTime);
        sqlSb.append("' and publicTime < '").append(endTime).append("';");
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlSb.toString());
        query.addEntity(Wbinfor.class);
        System.out.println(sqlSb);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Wbinfor> getWbinfosByHotEvent(String hotwid)
    {
        StringBuffer sqlSb = new StringBuffer("select w.* from hotevent_status_relate as r");
        sqlSb.append(" left join wbinfor as w on w.id = r.wid");
        sqlSb.append(" where r.hotwid = '").append(hotwid).append("'");
        
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlSb.toString());
        query.addEntity(Wbinfor.class);
        List<Wbinfor> list = query.list();
        if (list == null)
        {
            list = new ArrayList<Wbinfor>();
        }
        return list;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Wbinfor> getWbinfosByTime(String startTime, String endTime)
    {
        StringBuffer sqlSb = new StringBuffer("select * from wbinfor");
        if (StringUtils.isBlank(startTime))
        {
            if (StringUtils.isNotBlank(endTime))
            { // end不为空
                sqlSb.append(" where publicTime <='").append(endTime).append("'");
            }
        }
        else
        {
            if (StringUtils.isBlank(endTime))
            {// start不为空
                sqlSb.append(" where publicTime >='").append(startTime).append("'");
            }
            else
            {// 均不为空
                sqlSb.append(" where publicTime >='").append(startTime)
                        .append("' and publicTime <='").append(endTime);
            }
        }
        // 获取本地sql语句对象
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlSb.toString());
        // 设置将SQL表的别名和实体联系起来
        query.addEntity(Wbinfor.class);
        // 获取结果
        List<Wbinfor> list = query.list();
        if (list == null)
        {
            list = new ArrayList<Wbinfor>();
        }
        return list;
    }
    
    /**
     * 
     * 概要说明 : 保存信息 <br>
     * 详细说明 : <br>
     *
     * @param wbi  void
     * @see     com.weibo.jg.wb.SelectDaoImpl#save()
     * @author  by lj @ 2018年3月20日, 下午2:20:11
     */
    public void save(Wbinfor wbi)
    {
    	
        Session session=sessionFactory.getCurrentSession();
        session.save(wbi);
    }
    
    /**
     * 
     * 概要说明 : 查询地址是否已经存在<br>
     * 详细说明 :  <br>
     *
     * @param url 地址
     * @return  Wbinfor
     * @see     com.weibo.jg.wb.SelectDaoImpl#getWbinforByUrl()
     * @author  by lj @ 2018年3月20日, 下午5:49:05
     */
    public Wbinfor getWbinforByUrl(String url)
    {
        StringBuffer sqlbf = new  StringBuffer("") ;
        Session session=sessionFactory.getCurrentSession();
        sqlbf.append("select *  from wbinfor");
        
        sqlbf.append( " where url='" + url + "'");
        LogUtil.info(sqlbf.toString());
        //获取本地sql语句对象
        SQLQuery query=session.createSQLQuery(sqlbf.toString());
        query.addEntity(Wbinfor.class);
        //获取结果
        @SuppressWarnings("unchecked")
        List<Wbinfor> list=query.list();
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

	@Override
	public List<Object> selectPOIcount(String start, String end,String city) {
		    LogUtil.info("开始时间:" + start);
	        LogUtil.info("结束时间:" + end);
	        StringBuffer sqlbf = new  StringBuffer("") ;
	        Session session=sessionFactory.getCurrentSession();
	        //sqlbf.append("select poi.lon,poi.lat,count(poi.poiid)  from poi,statuses "
	    	//		+ "where  poi.poiid=statuses.poiid");
	        sqlbf.append("select lon,lat,count(id)  from wbinfor where ");
	        if (StringUtils.isBlank(start))
	        {
//	        	and  statuses. created_at>='2016-01-01' and statuses.created_at <='2016-07-02'
//	                	group by  poi.poiid
	            if (StringUtils.isNotBlank(end))
	            { // end不为空
	            	sqlbf.append( "   wbinfor.publicTime <='" + end + " 00:00'");
	            }
	        }
	        else
	        {
	            if (StringUtils.isBlank(end))
	            {// start不为空
	            	sqlbf.append("   wbinfor.publicTime>='" + start + " 24:00'");
	            
	            }
	            else
	            {// 均不为空
	            	sqlbf.append( "  wbinfor.publicTime>='" + start + " 24:00' and wbinfor.publicTime <='"
	                        + end + " 24:00'");
	            }
	        }
	        sqlbf.append(" and wbinfor.city= '"+city+"'");
	        sqlbf.append(" group by  uid;");
	        LogUtil.info(sqlbf.toString());
	        //获取本地sql语句对象
	        SQLQuery query=session.createSQLQuery(sqlbf.toString());
//	        query.addEntity(Object.class);
	        //获取结果
	        @SuppressWarnings("unchecked")
	        List<Object> list=query.list();
	        return list;
		
		
	}
    
}
