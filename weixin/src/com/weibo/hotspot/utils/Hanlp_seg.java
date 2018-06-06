package com.weibo.hotspot.utils;

import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class Hanlp_seg
{
    /**
     * 
     * 概要说明 : 分词 <br>
     * 详细说明 :  <br>
     * 
     * @param line
     * @return List<String>
     * @see hanlp.Hanlp_seg#segmentList()
     * @author by jiangj @ 2016年11月9日, 上午10:15:04
     */
    public static List<String> segmentList(String line)
    {
        List<Term> termList = HanLP.segment(line);
        ArrayList<String> result = new ArrayList<String>();
        for (Term term : termList)
        {
            String[] strlist = term.toString().split("(\\s+)|/");
            if (null != strlist && strlist.length == 2)
            {
                result.add(strlist[0]);
                result.add(strlist[1]);
            }
        }
        return result;
    }

    /**
     * 
     * 概要说明 : 分词 <br>
     * 详细说明 :  <br>
     * 
     * @param line
     * @return String
     * @see hanlp.Hanlp_seg#segmentString()
     * @author by jiangj @ 2016年11月9日, 上午10:14:58
     */
    public static String segmentString(String line)
    {
        List<Term> termList = HanLP.segment(line);
        ArrayList<String> resultlist = new ArrayList<String>();
        for (Term term : termList)
        {
            String[] strlist = term.toString().split("(\\s+)|/");
            if (null != strlist && strlist.length == 2)
            {
                resultlist.add(strlist[0]);
                resultlist.add(strlist[1]);
            }
        }
        String result = "";
        for (String str : resultlist)
        {
            result = result + str + " ";
        }
        return result;
    }

    public static void main(String[] args)
    {

        // 分词+词性标注
        System.out.println(HanLP.segment("2015年12月11日 - 年后这些股翻倍概率极大 春节后A股走势图大曝光"));

        System.out.println(Hanlp_seg.segmentList("2015年12月11日 - 年后这些股翻倍概率极大 春节后A股走势图大曝光"));

        // 句法分析
        // System.out.println(HanLP.parseDependency("把市场经济奉行的等价交换原则引入党的生活和国家机关政务活动中"));

        // 关键词提取
        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);

    }
}
