package com.weibo.hotspot.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.weibo.hotspot.bean.HotEventAna;
import com.weibo.jg.bean.Wbinfor;

/**
 * 概要说明 : 事件热度值计算.<br>
 * 详细说明 : <br>
 * @author jiangjian
 */
public class ExtractHotEvent
{

    static int HOTEventTop = 10; // 聚类K值
    
    private static double PlaceParameter = 0.1; // 微博地域分布

    private static double WeibocountParameter = 0.3;// 微博总数 参数

    private static double UserActivity = 0.3;// （3）用户活跃度 参数

    private static double UserCountParameter = 0.3; // 用户参与度 参数

    /**
     * 
     * 概要说明 : 根据聚类集合，提取事件因素 <br>
     * 详细说明 : . <br>
     * 
     * @param wbinfos
     * @return HotEvent
     * @see Candidate.ExtractHotEvent#exteactHotEvent()
     * @author by jiangj @ 2016年11月11日, 下午2:36:57
     */
    public static HotEventAna filterCandidateEvent(List<Wbinfor> wbinfos)
    {
        HotEventAna candidateEvent = new HotEventAna();//

        /** 关键词提取 */
        // 方法1: 基于词频的方法
        // List<String> topic = ExtractKeyWord.ChooseKeyWord(status);
        // 方法2: 基于互信息的方法
        // List<String> topic=GetTopic.getTopicOfCountWord ( status );
        // 方法3: 基于关联挖掘的方法
        List<String> topic = PhraseExtractor.extractPhrase(wbinfos);
        
        // System.out.println("【话题】："+topic.toString());
        candidateEvent.setTopic(topic.toString());
        candidateEvent.setWbinfos(wbinfos);
        candidateEvent.setStartTime(wbinfos.get(0).getPublicTime());
        return candidateEvent;
    }

    /**
     * 
     * 概要说明 : 事件参数提取 <br>
     * 详细说明 : . <br>
     * 
     * @param wbinfos
     * @return boolean
     * @see Candidate.ExtractHotEvent#analysisGrowth()
     * @author by jiangj @ 2016年11月10日, 下午2:01:36
     */
    public static HotEventAna getHotEventParameter(List<Wbinfor> wbinfos)
    {
        // 候选事件预处理
        HotEventAna hotevent = filterCandidateEvent(wbinfos);
        if (null != hotevent)
        {
            /* 候选参数获取 */
            List<String> user = new ArrayList<String>(); // 参与用户
            List<String> dislist = new ArrayList<String>(); // 涉及地点

            int userActivity = 0;
            int maxUserActivity = 0;
            for (Wbinfor info : wbinfos)
            {
                // 地点统计
                if (StringUtils.isNotBlank(info.getPlace()))
                {
                    String dis = info.getPlace();
                    if (!dislist.contains(dis))
                    {
                        dislist.add(dis);
                    }
                }
                // 用户活跃度统计
                userActivity = info.getReNum(); // 转发数
                userActivity += info.getLiNum(); // 点赞数
                userActivity += info.getCoNum();// 评论数
                if (maxUserActivity < userActivity)
                {
                    maxUserActivity = userActivity;
                }
                // 用户参与度统计
                if (!user.contains(info.getUid()))
                {
                    user.add(info.getUid());
                }
            }
            hotevent.setHotwid(wbinfos.get(0).getId());// 事件编号取事件中第一个微博的编号
            hotevent.setDistrictCount(dislist.size());// 事件地域分布
            hotevent.setUserActivity(userActivity);// 用户活跃度
            hotevent.setUserCount(user.size());// 参与用户个数
        }
        return hotevent;
    }

    public static List<HotEventAna> getEventScore(List<HotEventAna> hotevent,int top)
    {
        /* 候选事件最大微博总数、用户活跃度、用户参与度 统计 */
        int maxWeibocount = 0; // 微博总数
        int maxUserActivity = 0;// （3）用户活跃度
        int maxUserCount = 0; // 用户总数，用户参与度
        int count = 0;
        for (HotEventAna hot : hotevent)
        {
            count = hot.getWbinfosCount();
            if (maxWeibocount < count)
            {
                maxWeibocount = count;
            }
            count = hot.getUserActivity();
            if (maxUserActivity < count)
            {
                maxUserActivity = count;
            }
            count = hot.getUserCount();
            if (maxUserCount < count)
            {
                maxUserCount = count;
            }
        }

        /* 候选事件热度值计算 */
        Map<Integer, Double> hotscore = new TreeMap<Integer, Double>();
        double score = 0;
        for (int i = 0; i < hotevent.size(); i++)
        { // PlaceParameter = 0.1 微博地域分布
            score = PlaceParameter * hotevent.get(i).getDistrictCount()/8;// 微博地域分布
            if (maxWeibocount != 0)
            {//WeibocountParameter = 0.3 微博总数 参数
                score += WeibocountParameter * hotevent.get(i).getWbinfosCount() / maxWeibocount;// 微博总数 参数
            }
            if (maxUserActivity != 0)
            { //UserActivity = 0.3（3）用户活跃度 参数
                score += UserActivity * hotevent.get(i).getUserActivity() / maxUserActivity;// （3）用户活跃度 参数
            }
            if (maxUserCount != 0)
            {//UserCountParameter = 0.3 用户参与度 参数
                score += UserCountParameter * hotevent.get(i).getUserCount() / maxUserCount;// 用户参与度 参数
            }
            hotscore.put(i, score);
            hotevent.get(i).setScore(score);
        }

        // 热点排名，取top5
        List<Integer> hots = Util.sortIntegerDouble(hotscore, top);

        List<HotEventAna> newHotevent = new ArrayList<HotEventAna>();
        for (int num : hots)
        {
            newHotevent.add(hotevent.get(num));
        }
        return newHotevent;
    }

    public static List<HotEventAna> extractHotEvent(String resultfile,List<Wbinfor> status)
    {
     // 聚类结果提取
        Map<Integer, List<Integer>> statusID = Util.readTxtFile(resultfile);

        // 事件提取 （事件序号、事件中包含的微博在status中编号）
        Iterator<Entry<Integer, List<Integer>>> it = statusID.entrySet().iterator();

        List<HotEventAna> hotevents = new ArrayList<HotEventAna>();
        // 事件
        while (it.hasNext())
        {
            Entry<Integer, List<Integer>> entry = it.next();
            List<Integer> value = entry.getValue();
            if (entry.getKey() < 0 || value.size() < 5)
            {
                continue;
            }
            List<Wbinfor> newstatus = new ArrayList<Wbinfor>();

            for (int i : value)
            {
                newstatus.add(status.get(i));
            }
            HotEventAna hotevent = ExtractHotEvent.getHotEventParameter(newstatus);
            if (null != hotevent)
            {
                hotevents.add(hotevent);
            }
        }
        System.out.println("候选事件个数：" + hotevents.size());

        List<HotEventAna> resultevent = ExtractHotEvent.getEventScore(hotevents, HOTEventTop);
        return resultevent;
    }
}
