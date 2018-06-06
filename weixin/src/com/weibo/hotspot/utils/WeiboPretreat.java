package com.weibo.hotspot.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.weibo.hotspot.bean.LoadStopWord;
import com.weibo.jg.bean.Wbinfor;

public class WeiboPretreat
{
    /**
     * 
     * 概要说明 : 过滤无效微博 <br>
     * 详细说明 : 去除非中文符号. <br>
     *
     * @param wbinfo
     * @return  boolean
     * @see     weibo.WeiboPretreat#isEffectiveWeibo()
     * @author  by jiangj @ 2016年11月8日, 下午2:14:27
     */
    public static String isEffectiveWeibo(Wbinfor wbinfo)
    {
        // 语句中存在非中文符号
        String context = Util.extractweiboInfo(wbinfo.getInfortext());
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]{1,}");
        Matcher matcher = pattern.matcher(context);
        if (!matcher.find())
        {
            return null;
        }
        else
        {
            if (context.length() > 5)
            {
                return context;
            }
            return null;
        }
    }

    /**
     * 
     * 概要说明 : 获取句子关键词序列 ，保留词性标注<br>
     * 详细说明 :   <br>
     *
     * @param sentence
     * @return  String
     * @see     Candidate.WeiboPretreat#weiboPretreat()
     * @author  by jiangj @ 2016年12月26日, 下午5:58:10
     */
    public static String weiboPretreat(String sentence)
    {
        LoadStopWord stopword = LoadStopWord.getInstrance();
        String otherString = sentence;
        otherString = otherString.replaceAll("\\s+", "");
        otherString = otherString.replaceAll("　", "");

        otherString = otherString.replaceAll("\\|\\|null", "");
        List<String> list = Hanlp_seg.segmentList(otherString);

        String result = "";
        int count = 0;
        String regexchinese = "^[\\u4e00-\\u9fa5]+$";// 判断中文正则表达式
        for (int i = 0; i < list.size() - 1; i += 2)
        {
            if (list.get(i).length() < 2)
                continue;
            // 2、正则表达式删除非中文字符：
            if (!list.get(i).matches(regexchinese))
                continue;

            if (stopword.getStopword().contains(list.get(i)) || stopword.getStopverd().contains(list.get(i)) || stopword.getSina().contains(list.get(i)))
            {
                continue;
            }
             if ( list.get ( i + 1 ).indexOf ( "n" ) == 0 
//                     || list.get ( i + 1 ).indexOf ( "s" ) == 0
//                     || list.get ( i + 1 ).indexOf ( "t" ) == 0
                     || list.get ( i + 1 ).indexOf ( "v" ) == 0     ) {
            result += list.get(i) + " "+list.get ( i + 1 )+" ";
            count++;
             }
        }
        if (count == 0)
        {
            return null;
        }
        return result;

    }

    /**
     * 
     * 概要说明 : 微博文本处理. <br>
     * 详细说明 : 过滤微博中的非中文字符. <br>
     *
     * @param starttime
     * @param endtime
     * @return  List<Status>
     * @see     weibo.WeiboPretreat#getWeiboList()
     * @author  by jiangj @ 2016年11月8日, 下午2:07:40
     */
    public static Wbinfor SegmentWordOfWeibo(Wbinfor wbinfo)
    {
        Wbinfor copyInfo = new Wbinfor(wbinfo);
        // 去除全英文微博
        String text = isEffectiveWeibo(wbinfo);

        if (StringUtils.isNotBlank(text))
        {
            copyInfo.setInfortext(text);
            return copyInfo;
        }

        return null;
    }

    /**
     * 
     * 概要说明 : 批量处理微博文本. <br>
     * 详细说明 : 微博文本处理. <br>
     *
     * @param starttime
     * @param endtime
     * @return  List<Status>
     * @see     weibo.WeiboPretreat#getWeiboList()
     * @author  by jiangj @ 2016年11月8日, 下午2:07:40
     */
    public static List<Wbinfor> getWeiboList(List<Wbinfor> wbInfos)
    {
        List<Wbinfor> copyInfos = new ArrayList<Wbinfor>();

        for (int i = 0; i < wbInfos.size(); i++)
        {
            Wbinfor wbinfo = SegmentWordOfWeibo(wbInfos.get(i));
            if (null != wbinfo)
            {
                copyInfos.add(wbinfo);
            }
        }
        return copyInfos;
    }

    /**
     * 测试函数
     * 
     * @param args
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[])
    {
//        String starttime = "";
//        String endtime = "";
//        List<Wbinfor> statuses = DataSelect.selectStatuses(starttime, endtime);
//        List<Wbinfor> stas = getWeiboList(statuses);
//
//        List<String> emoticon = new ArrayList<String>();
//        for (Wbinfor sta : stas)
//        {
//            // System.out.println(sta.getText().length() + "\t 【正文：】" + sta.getText());
//            Pattern pattern = Pattern.compile("\\[.*?\\]");
//            Matcher matcher = pattern.matcher(sta.getText());
//            while (matcher.find())
//            {
//                String str = "\"" + matcher.group() + "\"";
//                if (!emoticon.contains(str))
//                {
//                    emoticon.add(str);
//                }
//            }
//        }
//        System.out.println(emoticon);
    }
}
