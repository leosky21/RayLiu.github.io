package com.weibo.hotspot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.weibo.hotspot.bean.Emoticon;
import com.weibo.hotspot.bean.Word;

public class Util
{
    /**
     * 根据值排序,并输出
     * 
     * @param keywordMap
     */
    public static List<Integer> sortIntegerDouble(Map<Integer, Double> keywordMap, Integer count)
    {

        List<Integer> result = new ArrayList<Integer>();
        List<Map.Entry<Integer, Double>> infoIds = new ArrayList<Map.Entry<Integer, Double>>(keywordMap.entrySet());

        // 排序
        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Double>>()
        {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
            {

                if (o2.getValue() > o1.getValue())
                    return 1;
                if (o2.getValue() - o1.getValue() >= -0.00000001 && o2.getValue() - o1.getValue() <= 0.000000001)
                    return 0;
                if (o2.getValue() < o1.getValue())
                    return -1;
                return 0;
            }
        });
        if (count > infoIds.size())
        {
            count = infoIds.size();
        }
//        System.out.println("排序后--------------------------------------------------：");
        // 排序后
        for (int i = 0; i < count; i++)
        {
            String id = infoIds.get(i).toString();
            int place = id.indexOf("=");
            result.add(Integer.parseInt(id.substring(0, place)));
//            System.out.println(id);
        }
        return result;
    }
    

    /**
     * 根据值排序,并输出
     * 
     * @param keywordMap
     */
    public static List<String> sortStringDouble ( Map<String, Double> keywordMap , Integer count ) 
    {
        List<String> result = new ArrayList<String> ( );
        if ( null == keywordMap || keywordMap.size ( ) == 0 )
        {
            return result;
        }
        List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>> ( keywordMap.entrySet ( ) );
        // 版本问题
        System.setProperty ( "java.util.Arrays.useLegacyMergeSort" , "true" );
        // 排序
        Collections.sort ( infoIds , new Comparator<Map.Entry<String, Double>> ( ) 
        {
            public int compare ( Map.Entry<String, Double> o1 , Map.Entry<String, Double> o2 ) 
            {

                if ( o2.getValue ( ) > o1.getValue ( ) )
                    return 1;
                if ( o2.getValue ( ) - o1.getValue ( ) >= -0.00000001
                        && o2.getValue ( ) - o1.getValue ( ) <= 0.000000001 )
                    return 0;
                if ( o2.getValue ( ) < o1.getValue ( ) )
                    return -1;
                return 0;
            }
        } );
        if ( count > infoIds.size ( ) )
        {
            count = infoIds.size ( );
        }
        // System.out
        // .println("排序后--------------------------------------------------：");
        // 排序后
        for ( int i = 0 ; i < count ; i++ ) 
        {
            String id = infoIds.get ( i ).toString ( );
            int place = id.indexOf ( "=" );
            id = id.substring ( 0 , place );
            result.add ( id );
            // System.out.println(id);
        }
        return result;
    }
    /**
     * 根据值排序,并输出
     * 
     * @param keywordMap
     */
    public static List<String> sortStringInteger ( Map<String, Integer> keywordMap , Integer count )
    {

        // Iterator<String> iter = keywordMap.keySet().iterator();
        // while (iter.hasNext()) {
        // String key = iter.next();
        //
        // System.out.println(key);
        // }

        List<String> result = new ArrayList<String> ( );
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>> (
                keywordMap.entrySet ( ) );

        // 排序
        Collections.sort ( infoIds , new Comparator<Map.Entry<String, Integer>> ( )
        {
            public int compare ( Map.Entry<String, Integer> o1 , Map.Entry<String, Integer> o2 ) 
            {
                return ( o2.getValue ( ) - o1.getValue ( ) );
            }
        } );
        if ( count > infoIds.size ( ) )
        {
            count = infoIds.size ( );
        }
        // System.out
        // .println("排序后--------------------------------------------------：");
        // 排序后
        for ( int i = 0 ; i < count ; i++ )
        {
            String id = infoIds.get ( i ).toString ( );
            int place = id.indexOf ( "=" );
            id = id.substring ( 0 , place );
            result.add ( id );
            // System.out.println(id);
        }
        return result;
    }
    
    
    
    /**
     * 计算 String类型 集合相似度
     * 
     * @param drug
     * @param drugList
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public static Double getListSimilarity_STRING(List<String> list1, List<String> list2)
    {
        Double simValue = 0.0;
        if (null == list1 || null == list2)
        {
            return simValue;
        }
        if (list1.size() == 0 || list2.size() == 0)
        {
            return simValue;
        }

        // 求出交集
        List<String> intersectionWord = new ArrayList<String>(list1);
        intersectionWord.retainAll(list2);
        Double intersection = (double) intersectionWord.size();

        // 求出并集
        List<String> unionofWord = new ArrayList<String>(list1);
        unionofWord.removeAll(list2);
        unionofWord.addAll(list2);
        Double unionof = (double) unionofWord.size();

        simValue = intersection / unionof;

        return simValue;
    }
    /**
     * 
     * 概要说明 : 计算   集合相似度 <br>
     * 详细说明 : TODO ADD Instruction. <br>
     *
     * @param line1
     * @param line2
     * @return  Double
     * @see     weibo.WeiboPretreat#getSimilarity()
     * @author  by jiangj @ 2016年11月9日, 上午10:56:22
     */
    public static Double getSimilarity(String line1,String line2 )
    {
        
        if (StringUtils.isBlank(line1)||StringUtils.isBlank(line2))
        {
            return -1.0;
        }
        List<String> list1=Arrays.asList(line1.split(" "));
        list1.remove(" ");//删除空格
        list1.remove("");
        List<String> list2=Arrays.asList(line2.split(" "));
        list2.remove(" ");
        list2.remove("");
        Double simValue = 0.0;

        if (list1.size() == 0 || list2.size() == 0)
        {
            return simValue;
        }
        // 求出交集
        List<String> intersectionWord = new ArrayList<String>(list1);
        intersectionWord.retainAll(list2);
        Double intersection = (double) intersectionWord.size();

        // 求出并集
        List<String> unionofWord = new ArrayList<String>(list1);
        unionofWord.removeAll(list2);
        unionofWord.addAll(list2);
        Double unionof = (double) unionofWord.size();

        // 计算关键词相似度
        simValue = intersection *100 / unionof;

        return simValue;
    }
    /**
     * 
     * 概要说明 :提取weibo.cn微博中的有效部分 <br>
     * 详细说明 : TODO ADD Instruction. <br>
     *
     * @param title     微博标题
     * @param context    微博正文
     * @return  String  处理后的微博标题
     * @see 类名.方法名
     * @author by jiangj @ 2016-9-28下午2:50:30
     */
    public static String extractweiboInfo(String context)
    {
        String concept = context;
        // 无关内容去除
        if (concept.indexOf(":发表了博文") < 5)
        {
            concept = concept.replaceAll(":发表了博文", "");
        }
        
       String sentence= concept.replaceAll ( "　" , "" );
       sentence=sentence.replaceAll ( " " , "" );
        sentence = sentence.replaceAll ( "——财新网讨论中" , "" );
        sentence = sentence.replaceAll ( "#我在看新闻#" , "" );
        sentence = sentence.replaceAll ( "来自@腾讯新闻客户端" , "" );
        sentence = sentence.replaceAll ( "\\(分享自 @(\\s|\\S)+\\)" , "" );
        sentence = sentence.replaceAll ( "\\(分享自 #(\\s|\\S)+#\\)" , "" );
        sentence = sentence.replaceAll ( "\\(来自@(\\s|\\S)+\\)" , "" );
        sentence = sentence.replaceAll ( "(来自腾讯新闻)" , "" );
        sentence = sentence.replaceAll ( "\\(@(\\s|\\S)+下载\\)" , "" );
        sentence = sentence.replaceAll ( "http://url.cn/[0-9a-zA-Z/]+" , "" );
        sentence = sentence.replaceAll ( "分享图片" , "" );
        sentence = sentence.replaceAll ( "@.*? " , " " );
        Pattern pattern1 = Pattern.compile("\\[.*?\\]");
        Matcher matcher1 = pattern1.matcher(sentence);
        if (matcher1.find())
        {
            List<String> emoticons = Emoticon.getEmoticon();

            for (String emo : emoticons)
            {
                if (sentence.indexOf(emo) >= 0)
                {
                    emo = emo.replaceAll("\\[", "\\\\[");
                    emo = emo.replaceAll("\\]", "\\\\]");

                    sentence = sentence.replaceAll(emo, "");
                }
            }
        }
        // 去除网址我在这里:http://t.cn/z8AMJlB
        Pattern pattern = Pattern.compile("(我在这里:)?http://t.cn/[a-zA-Z0-9]{1,}");
        String[] result = pattern.split(sentence);
        concept = "";
        for (int i = 0; i < result.length; i++)
        {
            concept += result[i];
        }
        return concept;
    }
    /**
     * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     * 
     * @param filePath
     */
    public static Map<Integer, List<Integer>> readTxtFile(String filePath) 
    {
        Map<Integer, List<Integer>> statusID = new TreeMap<Integer, List<Integer>>();
        try 
        {
            int i=0;
            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) 
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    int type=Integer.parseInt(lineTxt);
                    List<Integer> urllist = new ArrayList<Integer>();
                    if (statusID.containsKey(type))
                    {
                        urllist=statusID.get(type);
                    }
                    urllist.add(i);
                    statusID.put(type, urllist);
                    i++;
                }
                read.close();

            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return statusID;
    }
    
    /**
     * 
     * 概要说明 : 数据写入到指定PATH指定的文本中 <br>
     * 详细说明 :  <br>
     *
     * @param path
     * @param matrix  void
     * @see     clusterer.ClustererStatus#processHierarchical()
     * @author  by jiangj @ 2016年11月30日, 下午4:27:59
     */
    public static void processHierarchical(String path, List<String> matrix)
    {
        try
        {
            PrintStream out = new PrintStream(path);
            /* 行标、列标 */
            for (int i = 0; i < matrix.size(); ++i)
            {// 输出每次迭代更新的矩阵
                out.println(matrix.get(i));
            }

            out.close();
            System.out.println("Please check results in: " + path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 
     * 概要说明 : 数据写入到指定PATH指定的文本中 <br>
     * 详细说明 :  <br>
     *
     * @param path
     * @param matrix  void
     * @see     clusterer.ClustererStatus#processHierarchical()
     * @author  by jiangj @ 2016年11月30日, 下午4:27:59
     */
    public static void processHierarchical(String rlabelFile,
            int[][] matrix,List<Word> words)
    {
        try
        {
            PrintStream out = new PrintStream(rlabelFile, "utf-8");
            
            /* 行标、列标 */
            for (int i = 0; i < matrix.length; ++i)
            {// 输出每次迭代更新的矩阵
                out.print( words.get(i).getWord()+" ");
            }
            out.println("");
            for (int i = 0; i < matrix.length; ++i)
            {// 输出每次迭代更新的矩阵
                for (int j = 0; j < matrix.length - 1; ++j)
                {
                    out.print( matrix[i][j]  + " ");
                }
                out.println( matrix[i][matrix.length - 1] );
            }
           
            System.out.println("Please check results in: " + rlabelFile);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 
     * 概要说明 : 数据写入到指定PATH指定的文本中 <br>
     * 详细说明 :  <br>
     *
     * @param path
     * @param matrix  void
     * @see     clusterer.ClustererStatus#processHierarchical()
     * @author  by jiangj @ 2016年11月30日, 下午4:27:59
     */
    public static void processHierarchicalROW(String path,List<String> result,int count)
    {
        

        int row =result.size();//行
        try
        {
            PrintStream out = new PrintStream(path);
            /* 行标、列标 */
            out.println(row+"\t"+row+"\t"+count);
            
            for (int i = 0; i < result.size(); ++i)
            {// 输出每次迭代更新的矩阵
                out.println(result.get(i));
            }
            
            out.close();
            System.out.println("Please check results in: " + path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
