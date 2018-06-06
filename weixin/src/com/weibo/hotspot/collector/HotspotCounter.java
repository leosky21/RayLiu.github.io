package com.weibo.hotspot.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.weibo.hotspot.bean.HotEventAna;
import com.weibo.hotspot.bean.Word;
import com.weibo.hotspot.dao.IHotEventDao;
import com.weibo.hotspot.utils.Command;
import com.weibo.hotspot.utils.ExtractHotEvent;
import com.weibo.hotspot.utils.ShortText;
import com.weibo.hotspot.utils.Util;
import com.weibo.hotspot.utils.WeiboPretreat;
import com.weibo.jg.bean.Wbinfor;
import com.weibo.jg.wb.SelectDao;
import com.weixin.xm.utils.SpringContextUtil;

/**
 * 热点提取
 */
public class HotspotCounter
{
    private int ClustererNum = 10; // 聚类K值
    
    private String classpath = this.getClass().getResource("/").getPath();
    
    private SelectDao selectDao;
    
    private IHotEventDao hotEventDao;
    
    private static HotspotCounter instance = new HotspotCounter();
    
    private HotspotCounter()
    {
        if (selectDao == null)
        {
            selectDao = SpringContextUtil.getBean("selectDao");
        }
        if (hotEventDao == null)
        {
            hotEventDao = SpringContextUtil.getBean("hotEventDao");
        }
    }
    
    public static HotspotCounter getInstance()
    {
        return instance;
    }

    /**
     * 概要说明 : 统计文本中各词的数量.<br>
     * 详细说明 : <br>
     * 
     * @param wbinfo
     * @return ShortText
     * @see HotspotCounter.WordCount#getCountWordOfStatus
     */
    public ShortText getCountWordOfStatus(Wbinfor wbinfo)
    {
        ShortText shtext = new ShortText();
        shtext.setNsflag(false);

        // 分析文本
        List<String> words = new ArrayList<String>();

        // 分词，去停用词
        String segment = WeiboPretreat.weiboPretreat(wbinfo.getInfortext());
        if (StringUtils.isBlank(segment))
        {
            return null;
        }

        List<String> ws = Arrays.asList(segment.split(" "));
        for (int i = 0; i < ws.size() - 1; i += 2)
        {
            if (ws.get(i + 1).indexOf("ns") == 0)
            {
                shtext.setNsflag(true);
            }
            if (StringUtils.isEmpty(ws.get(i)) || words.contains(ws.get(i)))
            {
                continue;
            }
            String word = ws.get(i);
            // 统计文本中出现的词语
            words.add(word);
        }

        String hashtag = "";
        Pattern pattern = Pattern.compile("#.*?#");

        Matcher matcher = pattern.matcher(wbinfo.getInfortext());
        if (matcher.find())
        {
            hashtag = matcher.group();
        }

        String timetag = "";// 2016-08-19 20:40:34
        if (StringUtils.isNotBlank(wbinfo.getPublicTime()))
        {
            timetag = wbinfo.getPublicTime().substring(11, 13);
        }

        shtext.setWid(wbinfo.getId());
        shtext.setContent(wbinfo.getInfortext());
        shtext.setWordsList(words);
        shtext.setCountInteger(ws.size());
        shtext.setTimetag(timetag);
        shtext.setHashtag(hashtag);
        return shtext;
    }

    /**
     * 
     * 概要说明 : 针对每条微博，统计关键词 <br>
     * 详细说明 : . <br>
     * 
     * @param words
     * @param shtext
     * @return List<Word>
     * @see HotspotCounter.WordCount#WordInMAP()
     * @author by jiangj @ 2016年12月6日, 上午9:40:02
     */
    public Map<String, Word> WordInMAP(Map<String, Word> words,
            ShortText shtext)
    {
        String timefag = shtext.getTimetag();
        String wid = shtext.getWid();
        List<String> textword = shtext.getWordsList();
        // 统计每个时间段的文本总数
        for (int j = 0; j < textword.size(); j++)
        {
            // 出现过的词语
            String key = textword.get(j);

            Word newword = new Word();
            Map<String, Integer> textcount = new HashMap<String, Integer>();
            if (words.containsKey(key))
            {
                // 获取word对应的属性: 词语在文本中出现个数.
                newword = words.get(key);
                textcount = words.get(key).getTextcount();
            }
            // 未出现过的词语
            else
            {
                newword.setWord(key);
            }

            textcount.put(wid, 1);
            newword.setTextcount(textcount);

            newword.put(wid, timefag);
            words.put(key, newword);
        }
        return words;
    }

    public double getEntropy(Word word)
    {
        double entroy = 0;
        int df = word.getTextcount().size();
        double p = word.getTextcount0().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount1().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount2().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount3().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount4().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount5().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount6().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount7().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount8().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount9().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount10().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount11().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount12().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount13().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount14().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount15().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount16().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount17().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount18().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount19().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount20().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount21().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount22().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount23().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        p = word.getTextcount24().size() * 1.0 / df;
        if (p > 0)
        {
            entroy += p * Math.log(p);
        }
        return entroy * (-1.0);
    }

    /**
     * 概要说明 : 从微博信息队列中获取微博关键词 <br>
     * 详细说明 : . <br>
     * 
     * @param wbinfos
     * @return List<String>
     * @see HotspotCounter.WordCount#getKeyWord()
     * @author by jiangj @ 2016年12月5日, 下午4:13:04
     */
    public List<ShortText> getShortText(List<Wbinfor> wbinfos)
    {
        // 分析每一个微博
        List<ShortText> shorttext = new ArrayList<ShortText>();
        for (int i = 0; i < wbinfos.size(); i++)
        {
            ShortText st = getCountWordOfStatus(wbinfos.get(i));
            if (null == st)
            {
                continue;
            }
            shorttext.add(st);
        }
        return shorttext;

    }

    /**
     * 
     * 概要说明 : 获取一天时间内的微博关键词 <br>
     * 详细说明 : . <br>
     * 
     * @param statuses
     * @return List<String>
     * @see HotspotCounter.WordCount#getKeyWord()
     * @author by jiangj @ 2016年12月5日, 下午4:13:04
     */
    public List<Word> getKeyWordInOneDay(List<ShortText> shorttext)
    {

        // 统计词语出现的文本总数
        Map<String, Word> wordsMap = new HashMap<String, Word>();
        for (int i = 0; i < shorttext.size(); i++)
        {
            wordsMap = WordInMAP(wordsMap, shorttext.get(i));
        }

        // 计算每个词语的权值
        double entropyAll = 0;
        int count = 0;

        Iterator<Entry<String, Word>> it = wordsMap.entrySet().iterator();
        while (it.hasNext())
        {
            Word wordobject = it.next().getValue();
            // 熵
            double entroy = getEntropy(wordobject);
            wordobject.setSubjectEntropy(entroy);

            // if (wordobject.getTextcount().size()>1){
            // System.out.println("【词语】："+wordobject.getWord()+"\t【出现次数】："+wordobject.getTextcount().size());
            // }

            // 取熵值大于0的词语，提取主题词
            if (entroy > 0)
            {
                entropyAll += entroy;
                count++;
            }
        }
        // 求平均值
        entropyAll = entropyAll / count;

        // 词语筛选
        List<Word> resultWord = new ArrayList<Word>();
        Iterator<Entry<String, Word>> newit = wordsMap.entrySet().iterator();
        while (newit.hasNext())
        {
            Word wordobject = newit.next().getValue();

            if (wordobject.getSubjectEntropy() > entropyAll)
            {
                // System.out.print("【 熵 频率】:" + wordobject.getSubjectEntropy()
                // + "\t【平均频率】：" + entropyAll);
                // System.out.println("\t【词语】：" + wordobject.getWord());
                resultWord.add(wordobject);
            }
        }

        return resultWord;
    }

    /**
     * 
     * 概要说明 : 获取两个词的共现 <br>
     * 详细说明 : . <br>
     * 
     * @param word1
     * @param word2
     * @return double
     * @see HotspotCounter.WordCount#getCoOccurrence()
     * @author by jiangj @ 2016年12月6日, 下午4:51:04
     */
    private int getCoOccurrence(Word word1, Word word2)
    {
        int number = 0;

        Map<String, Integer> textcount1 = word1.getTextcount();
        Map<String, Integer> textcount2 = word2.getTextcount();

        Iterator<Entry<String, Integer>> it = textcount1.entrySet().iterator();
        while (it.hasNext())
        {
            String key = it.next().getKey();

            if (textcount2.containsKey(key))
            {
                number++;
            }
        }

        return number;
    }

    /**
     * 概要说明 : 将输入数据转化为矩阵 <br>
     * 详细说明 : <br>
     * 
     * @param words
     * @return int[][]
     * @see com.weibo.hotspot.collector.HotspotCounter#loadMatrix
     * @author jiangj @ 2016年11月4日, 上午9:05:04
     */
    private int[][] loadMatrix(List<Word> words)
    {
        // 将微博中的词语编号
        int[][] matrix = new int[words.size()][words.size()];
        for (int i = 0; i < words.size(); ++i)
        {
            for (int j = i + 1; j < words.size(); j++)
            {
                matrix[i][j] = getCoOccurrence(words.get(i), words.get(j));
            }

        }
        return matrix;
    }

    /**
     * 概要说明 : 根据微博ID判断是否已存在. <br>
     * 详细说明 : . <br>
     * 
     * @param shorttext
     * @param wid
     * @return boolean
     * @see HotspotCounter.WordCount#ishaveWbinfo()
     * @author by jiangj @ 2016年12月19日, 下午2:14:12
     */
    public ShortText ishaveWbinfo(List<ShortText> shorttext, String wid)
    {
        for (ShortText st : shorttext)
        {
            if (st.getWid().equals(wid))
            {
                return st;
            }
        }
        return null;
    }

    /**
     * 
     * 概要说明 : 数据写入到指定PATH指定的文本中 <br>
     * 详细说明 : . <br>
     * void
     * 
     * @see HotspotCounter.WordCount#processHierarchical()
     * @author by jiangj @ 2016年12月19日, 下午2:31:52
     */
    public void processHierarchical(List<Word> words, String docfile)
    {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < words.size(); i++)
        {
            String newresult = "" + i + "\t";
            Map<String, Integer> textcount = words.get(i).getTextcount();
            Iterator<Entry<String, Integer>> it = textcount.entrySet()
                    .iterator();
            while (it.hasNext())
            {
                String key = it.next().getKey();
                newresult += key + "\t";
            }
            result.add(newresult);
        }

        Util.processHierarchical(docfile, result);
    }
    
    /**
     * 概要说明 : 格式转换命令调用 <br>
     * 详细说明 : <br>
     * 
     * @param clmethod
     * @param sim
     * @param docfile
     * @param resultfile void
     * @see com.weibo.hotspot.collector.HotspotCounter#commandProcess
     * @author jiangj @ 2016年12月7日, 上午9:56:01
     */
    public void commandProcess(String clmethod, String sim,
            String docfile, String resultfile)
    {
        String matfile = classpath + "Cluto/perl/weibohot.mat";

        // 微博预处理结果 文件格式转化
        String dacmatpath = classpath + "Cluto/doc2mat-1.0/doc2mat";
        String cmd = "perl " + dacmatpath + " -nlskip=1 " + docfile + " " + matfile;
        String[] commandPerl = new String[] { "cmd.exe", "/c", cmd };
        Command.exeCmd(commandPerl);
        System.out.println("Please check results in: " + matfile);

        /* TODO 需要配置 vcluster 的环境变量 */
        cmd = "vcluster -clmethod=" + clmethod + " -sim=" + sim
                + " -showsummaries=cliques -clustfile=" + resultfile + " "
                + matfile + " " + ClustererNum;
        String[] commandCluto = new String[] { "cmd.exe", "/c", cmd };
        Command.exeCmd(commandCluto);
        System.out.println("Please check results in: " + resultfile);
    }

    /**
     * 概要说明 : 对获取的主题词进行聚类 ，返回主题词簇.<br>
     * 详细说明 : <br>
     * 
     * @param words
     * @param clmethod 聚类算法，bagglo是CLUTO中短文本聚类算法
     * @param sim 
     * @return Map<Integer,List<String>>
     * @see com.weibo.hotspot.collector.HotspotCounter#doClute
     * @author jiangj @ 2016年12月28日, 上午11:41:56
     */
    public Map<Integer, List<String>> doClute(List<Word> words, String clmethod, String sim)
    {
        // 统计共现关系
        int[][] matrix = loadMatrix(words);
//        String rlabelFile = "./Cluto/perl/cooccurrence.txt";
        String rlabelFile = classpath + "files/Cluto/perl/cooccurrence.txt";
        Util.processHierarchical(rlabelFile, matrix, words);

        // 词语间关系写入文本
        String docfile = classpath + "files/Cluto/perl/cooccurrence.raw";
        processHierarchical(words, docfile);

        // 聚类结果路径
        String resultfile = classpath + "files/Cluto/perl/clutoResult.txt";

        // 聚类 , baggle方法
        commandProcess(clmethod, sim, docfile, resultfile);

        // 聚类结果提取
        Map<Integer, List<Integer>> wordID = Util.readTxtFile(resultfile);

        // 事件提取 （事件序号、事件中包含的微博在status中编号）
        Iterator<Entry<Integer, List<Integer>>> it = wordID.entrySet().iterator();
        Map<Integer, List<String>> HOTwordMap = new TreeMap<Integer, List<String>>();
        // 事件
        while (it.hasNext())
        {
            Entry<Integer, List<Integer>> entry = it.next();
            List<Integer> value = entry.getValue();
            if (value.size() < 2)
            {
                continue;
            }
            if (entry.getKey() < 0)
            {
                continue;
            }
            List<String> widList = new ArrayList<String>();
            for (int i : value)
            {
                if (i >= words.size())
                {
                    continue;//TODO 原本没有这句，不理解为什么直接取clutoResult.txt中的数值
                }
                widList.add(words.get(i).getWord());
                System.out.print(words.get(i).getWord() + "\t");
            }
            HOTwordMap.put(entry.getKey(), widList);
            System.out.println("");
        }
        return HOTwordMap;
    }

    /**
     * 概要说明 : 微博关联.<br>
     * 详细说明 : <br>
     * 
     * @param wbinfos
     * @param shorttext
     * @param HOTwordMap
     * @return Map<Integer,List<Wbinfor>>
     * @see com.weibo.hotspot.collector.HotspotCounter#relatedWbinfor
     * @author jiangj @ 2016年12月28日, 下午2:19:05
     */
    public Map<Integer, List<Wbinfor>> relatedWbinfor(List<Wbinfor> wbinfos,
            List<ShortText> shorttext, Map<Integer, List<String>> HOTwordMap)
    {
        Map<Integer, List<Wbinfor>> eventWbinfor = new TreeMap<Integer, List<Wbinfor>>();
        List<Wbinfor> exWbinfos = wbinfos;
        // 微博关联
        for (int i = 0; i < exWbinfos.size(); i++)
        {
            ShortText st = ishaveWbinfo(shorttext, exWbinfos.get(i).getId());
            if (null == st)
            {
                continue;
            }
            if (false == st.isNsflag())
            {
                continue;
            }
            Iterator<Entry<Integer, List<String>>> ittext = HOTwordMap
                    .entrySet().iterator();
            int maxcount = -1;
            int maxhotid = -1;
            boolean hashflag = false;
            String hashtag = st.getHashtag();
            // 事件
            while (ittext.hasNext())
            {

                int hotid = ittext.next().getKey();
                List<String> wordlist = HOTwordMap.get(hotid);
                int count = 0;

                for (int j = 0; j < wordlist.size(); j++)
                {
                    if (StringUtils.isNotBlank(hashtag))
                    {
                        if (hashtag.indexOf(wordlist.get(j)) >= 0)
                        {
                            hashflag = true;
                            maxhotid = hotid;
                            break;
                        }
                    }

                    if (exWbinfos.get(i).getInfortext()
                            .indexOf(wordlist.get(j)) >= 0)
                    {
                        count++;
                    }

                }
                if (hashflag == true)
                {
                    break;
                }
                if (maxcount < count && count >= 1)
                {
                    maxhotid = hotid;
                }
            }
            if (maxhotid >= 0)
            {
                List<Wbinfor> stat = new ArrayList<Wbinfor>();
                if (eventWbinfor.containsKey(maxhotid))
                {
                    stat = eventWbinfor.get(maxhotid);
                }
                stat.add(exWbinfos.get(i));
                eventWbinfor.put(maxhotid, stat);
                // exstatus.remove(i);
                // i--;
            }
        }
        return eventWbinfor;
    }

    /**
     * 
     * 概要说明 : 事件特征提取<br>
     * 详细说明 : . <br>
     * 
     * @param eventStatus
     * @return List<HotEvent>
     * @see HotspotCounter.WordCount#eventfeatureExtraction()
     * @author by jiangj @ 2016年12月28日, 下午2:30:56
     */

    public List<HotEventAna> eventfeatureExtraction(Map<Integer, List<Wbinfor>> eventStatus,
            Map<Integer, List<String>> HOTwordMap)
    {
        List<HotEventAna> hotevents = new ArrayList<HotEventAna>();

        Iterator<Entry<Integer, List<Wbinfor>>> itstatu = eventStatus.entrySet().iterator();

        while (itstatu.hasNext())
        {
            int hotid = itstatu.next().getKey();
            List<Wbinfor> newWbinfos = eventStatus.get(hotid);

            HotEventAna hotevent = ExtractHotEvent.getHotEventParameter(newWbinfos);
            hotevent.setWords(HOTwordMap.get(hotid));
            if (null != hotevent)
            {
                hotevents.add(hotevent);
            }
        }
        return hotevents;
    }

    /**
     * 概要说明 : 计算两类微博的相似度 <br>
     * 详细说明 : . <br>
     * 
     * @param wbinfos1
     * @param wbinfos2
     * @param shorttext
     * @return boolean
     * @see HotspotCounter.WordCount#getSimStatus()
     * @author by jiangj @ 2016年12月28日, 下午3:49:22
     */
    public boolean getSimStatus(List<Wbinfor> wbinfos1, List<Wbinfor> wbinfos2, List<ShortText> shorttext)
    {
        int simCount = 0;
        int simUser = 0;

        for (int i = 0; i < wbinfos1.size(); i++)
        {
            ShortText sti = ishaveWbinfo(shorttext, wbinfos1.get(i).getId());
            for (int j = 0; j < wbinfos2.size(); j++)
            {
                ShortText stj = ishaveWbinfo(shorttext, wbinfos2.get(j).getId());
                // 相似度值
                double sim = Util.getListSimilarity_STRING(sti.getWordsList(),
                        stj.getWordsList());
                if (sim > 0.5)
                {
                    simCount++;
                }

                if (wbinfos1.get(i).getUid().equals(wbinfos2.get(j).getUid()))
                {
                    simUser++;
                }
            }
        }
        double simi = simCount * 1.0 / wbinfos1.size(); // 相似度大于0.5的微博数占微博总数的比例

        double simj = simCount * 1.0 / wbinfos2.size();

        if (simi > 0.6 || simj > 0.6)
        {
            return true;
        }

        simi = simUser * 1.0 / wbinfos1.size();
        simj = simUser * 1.0 / wbinfos2.size();
        if (simi > 0.6 || simj > 0.6)
        {
            return true;
        }

        return false;
    }

    /**
     * 概要说明 : 话题合并<br>
     * 详细说明 : . <br>
     * 
     * @param eventStatus
     * @see HotspotCounter.WordCount#topicMerging()
     * @author by jiangj @ 2016年12月28日, 下午2:57:59
     */
    public Map<Integer, List<Wbinfor>> topicMerging( Map<Integer, List<Wbinfor>> eventStatus,List<ShortText> shorttext)
    {
        List<Integer> keylist=new ArrayList<Integer>();
        
        for (int i=0;i<=10;i++)
        {
            if (!eventStatus.containsKey(i))
            {
                continue;
            }
            System.out.println("【事件编号】："+i);
            keylist.add(i);
        }
        for (int i=0;i<keylist.size();i++)
        {
            List<Wbinfor>  statusi=eventStatus.get(keylist.get(i));
            boolean flag=false;
            for (int j=i+1;j<keylist.size();j++)
            {
                List<Wbinfor>  statusj=eventStatus.get(keylist.get(i));
                flag=getSimStatus(statusi,  statusj, shorttext);
                if (true==flag )
                {
                    keylist.remove(j);
                    statusi.addAll(statusj);
                    break;
                }
            }
            //如果两类微博相似，合并
            //关联主题词簇后，通过包含的微博相似度、微博发布者相似度对每个主题词簇进行话题合并
            if (true==flag)
            {
                eventStatus.put(keylist.get(i), statusi);
            }
        }
        return eventStatus;

        
    }
    
    /**
     * 概要说明 : 热点事件分析.<br>
     * 详细说明 : <br>
     * 
     * @param city 地域，不允许为空
     * @param startTime 开始时间（格式 2018-03-27 00:00），不允许为空
     * @param endTime 结束时间（格式 2018-03-27 24:00），不允许为空
     * @see com.weibo.hotspot.collector.HotspotCounter#culHotspot
     * @author zyy @ 2018-3-27, 上午9:47:53
     */
    public void culHotspot(String city, String startTime, String endTime)
    {
        // 获取微博信息
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<Wbinfor> wbinfors = selectDao.getWbinforsByCityAndTime(city, startTime, endTime);
        if (wbinfors == null || wbinfors.size() == 0)
        {
        	
            return;
        }
        
        // 微博预处理
        List<Wbinfor> infoList = WeiboPretreat.getWeiboList(wbinfors);
        if (infoList == null || infoList.size() == 0)
        {
            return;
        }
        
        // 微博信息关键词数量统计
        List<ShortText> shorttext = getShortText(infoList);
        if (shorttext == null || shorttext.size() == 0)
        {
            return;
        }
        
        // 获取主题词
        List<Word> words = getKeyWordInOneDay(shorttext);
        if (words == null || words.size() == 0)
        {
            return;
        }
        
        // 聚类，获取主题词簇
        Map<Integer, List<String>> HOTwordMap = doClute(words, "bagglo", "cos");
        
        // 微博关联
        Map<Integer, List<Wbinfor>> eventWbinfo = relatedWbinfor(infoList, shorttext, HOTwordMap);
        
        // 话题合并
        eventWbinfo = topicMerging(eventWbinfo, shorttext);
        
        // 事件特征提取
        List<HotEventAna> hotevents = eventfeatureExtraction(eventWbinfo, HOTwordMap);
        System.out.println("候选事件个数：" + hotevents.size());
        
        // 事件热度值计算
        List<HotEventAna> resultevent = ExtractHotEvent.getEventScore(hotevents, 10);
        if (resultevent == null || resultevent.size() == 0)
        {
            return;
        }
        
        // 删除当天上一轮计算的热点事件
        hotEventDao.delete(city, startTime, endTime);
        // 保存热点事件
        for (HotEventAna hotEventAna : resultevent)
        {
            hotEventAna.setCity(city);
            hotEventDao.save(hotEventAna);
        }
    }
}
