package com.weibo.jg.collector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weibo.hotspot.bean.HotEvent;
import com.weibo.hotspot.collector.HotspotCounter;
import com.weibo.hotspot.dao.IHotEventDao;
import com.weibo.jg.bean.Links;
import com.weibo.jg.bean.NodeWord;
import com.weibo.jg.bean.Point;
import com.weibo.jg.bean.Wbinfor;
import com.weibo.jg.bean.wbpoint;
import com.weibo.jg.wb.DateJsonValueProcessor;
import com.weibo.jg.wb.SelectDao;
import com.weixin.xm.utils.LogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/selectController") //RequestMapping是一个用来处理请求地址映射的注解,可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
public class SelectController
{

    @Autowired //@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
    private SelectDao selectDao;
    
    @Autowired
    private IHotEventDao hotEventDao;

    @RequestMapping("/getSelectJson")
    public void getSelectJson(String start, String end, HttpServletRequest request, HttpServletResponse response)
    {
        LogUtil.info("开始时间:" + start);
        LogUtil.info("结束时间:" + end);
        List<Wbinfor> list = selectDao.getWbinfosByTime(start, end);
        if (null != list && list.size() > 0)
        {

            List<Point> points = new ArrayList<Point>();
            for (Wbinfor s : list)
            {
                Point point = new Point();
                point.setLng(s.getLon());
                point.setLat(s.getLat());
                points.add(point);
            }
            JSONArray jsonArray = JSONArray.fromObject(points);
            JSONObject json = new JSONObject();
            json.accumulate("result", jsonArray);
            String result = json.toString();
            LogUtil.info("json:" + result);

            // response的响应方式
            PrintWriter out = null;
            // LogUtil.info(result);
            // 设置响应数据类型，这里是json
            response.setContentType("application/json");
            // 设置响应编码格式，防止乱码
            response.setCharacterEncoding("UTF-8");
            try
            {
                out = response.getWriter();
                out.write(result);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                out.close();
            }
        }
    }
    
    /**
     * 概要说明 : 执行热点分析.<br>
     * 详细说明 : <br>
     * 
     * @param request
     * @param response
     * @throws IOException void
     * @see com.weibo.jg.collector.SelectController#handleHotspot
     * @author zyy @ 2018-3-27, 上午9:34:51
     */
    @RequestMapping("/handleHotspot")
    public void handleHotspot(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String city = request.getParameter("city");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.isBlank(city) || StringUtils.isBlank(startTime))
        {
            response.getWriter().print("paramError");
            return;
        }
        if (StringUtils.isNotBlank(endTime))
        {
            endTime += " 24:00";
        }
        else
        {
            endTime = startTime + " 24:00";
        }
        startTime += " 00:00";
        
        HotspotCounter.getInstance().culHotspot(city, startTime, endTime);
        
        response.getWriter().print("success");
    }

    @RequestMapping("/getSelectHotEvent")
    public void getSelectHotEvent(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 处理查询参数
        String city = request.getParameter("city");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.isBlank(city) || StringUtils.isBlank(startTime))
        {
            response.getWriter().print("paramError");
            return;
        }
        startTime += " 00:00:00";
        if (StringUtils.isNotBlank(endTime))
        {
            endTime += "23:59:59";
        }
        
        // 查询热点事件
        List<HotEvent> list = hotEventDao.getHotEventsByCityAndTime(city, startTime, endTime);
        if (null != list && list.size() > 0)
        {
            JsonConfig config = new JsonConfig();
            config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < list.size(); i++)
            {
                JSONObject json = JSONObject.fromObject(list.get(i), config);
                jsonArray.add(json);
            }

            JSONObject json = new JSONObject();
            json.accumulate("result", jsonArray);
            String result = json.toString();
            LogUtil.info("json:" + result);

            // 设置响应数据类型，这里是json
            response.setContentType("application/json");
            // 设置响应编码格式，防止乱码
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
        }
    }
    
    /**
     * 概要说明 : 根据热点事件获取微博信息，并生成坐标点集合.<br>
     * 详细说明 : <br>
     * 
     * @param hotwid
     * @param request
     * @param response void
     * @see com.weibo.jg.collector.SelectController#getSelectHoteventStatusRelate
     * @author zyy @ 2018-3-26, 下午5:02:04
     */
    @RequestMapping("/getSelectHoteventStatusRelate")
    public void getSelectHoteventStatusRelate(String hotwid, HttpServletRequest request, HttpServletResponse response)
    {
        List<Wbinfor> wbinfos = selectDao.getWbinfosByHotEvent(hotwid);//根据热点事件获取对应微博
       // System.out.println(wbinfos.size());
        Set<String> MID=new TreeSet();
        
        if (null != wbinfos && wbinfos.size() > 0)
        {

            List<wbpoint> points = new ArrayList<wbpoint>();
            for (int i = 0; i < wbinfos.size(); i++)
            {
                wbpoint point = new wbpoint();
               
                if(MID.contains(wbinfos.get(i).getUid())){
                	continue;
                }
                else{
                	// System.out.println("id"+wbinfos.get(i).getUid());
                	MID.add(wbinfos.get(i).getUid());
                }
                	
                point.setLng((String) wbinfos.get(i).getLon());
                point.setLat((String) wbinfos.get(i).getLat());
                point.setplace((String) wbinfos.get(i).getPlace());
                point.setinfortext((String) wbinfos.get(i).getInfortext());
                point.setpublicTime((String) wbinfos.get(i).getPublicTime());
                point.seturl((String) wbinfos.get(i).getUrl());
                points.add(point);
            }

            JSONArray jsonArray = JSONArray.fromObject(points);
            JSONObject json = new JSONObject();
            json.accumulate("result", jsonArray);
            String result = json.toString();
            LogUtil.info("json:" + result);

            // response的响应方式
            PrintWriter out = null;
            // 设置响应数据类型，这里是json
            response.setContentType("application/json");
            // 设置响应编码格式，防止乱码
            response.setCharacterEncoding("UTF-8");
            try
            {
                out = response.getWriter();
                out.write(result);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                out.close();
            }
        }
    }

    @RequestMapping("/getWordCoccurrence")
    public void getWordCoccurrence(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String encoding = "utf8";
        String path = this.getClass().getResource("/").getPath() + "files/Cluto/perl/cooccurrence.txt"; 
        List<String> words = new ArrayList<String>(); // 所有词语
        int[][] matrix = new int[100][100];

        try
        {
            InputStreamReader read = new InputStreamReader(new FileInputStream(path), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;

            // 字符输入流
            int count = 0;
            while ((lineTxt = bufferedReader.readLine()) != null)
            {
                // 读取第一行的词语
                String[] wordcount = lineTxt.split(" ");
                System.out.println(wordcount);
                if (count == 0)
                {
                    for (String strw : wordcount)
                    {
                        if (StringUtils.isNotBlank(strw))
                        {
                            words.add(strw);
                        }
                    }
                }
                // 读取其他行的词语
                else
                {
                    for (int i = 0; i < wordcount.length; i++)
                    {
                        if (StringUtils.isNotBlank(wordcount[i]))
                        {
                            matrix[count - 1][i] = Integer.parseInt(wordcount[i]);
                        }
                    }

                }
                count++;
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       
        // br.close();
        // fr.close();
        List<NodeWord> nodes = new ArrayList<NodeWord>(); // 词语
        List<Links> links = new ArrayList<Links>(); // 词语间关系

        System.out.println(words);

        for (String word : words)
        {
            NodeWord anode = new NodeWord();
            anode.setCategory(0);
            anode.setName(word);
            anode.setValue(6);
            anode.setLabel(word);
            nodes.add(anode);
        }
        for (int i = 0; i < words.size(); i++)
        {
            for (int j = i + 1; j < words.size(); j++)
            {
                if (matrix[i][j] > 0)
                {
                    Links link = new Links();
                    link.setSource(words.get(i));
                    link.setTarget(words.get(j));
                    link.setWeight(matrix[i][j]);
                    link.setName("共现");
                    links.add(link);
                }

            }
        }

        JSONArray jsonnode = JSONArray.fromObject(nodes);
        JSONArray jsonlink = JSONArray.fromObject(links);
        JSONObject json = new JSONObject();
        json.accumulate("node", jsonnode); //累积jsonnode到这个node下
        json.accumulate("link", jsonlink);
        String result = json.toString();
        LogUtil.info("json:" + result);

        // response的响应方式
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
    }
    @RequestMapping("/getSelectPoiCount")
    public void getSelectPoiCount(HttpServletRequest request, HttpServletResponse response) throws java.text.ParseException
    {
        String city = request.getParameter("city");
        String start = request.getParameter("startTime");
        String end = request.getParameter("endTime");
    	
    	System.out.println("getSelectPoiCount");
        List<Object> list = selectDao.selectPOIcount(start, end,city);////获取时间范围内的微博地点数量
        System.out.println(list.size());
        if (null != list && list.size() > 0)
        {

            List<Point> points = new ArrayList<Point>();
            for (int i = 0; i < list.size(); i++)
            {
            	Object[] obj = (Object[]) list.get(i);
                Point point = new Point();
                point.setLng((String) obj[0]);
                point.setLat((String) obj[1]);
                point.setCount(((BigInteger) obj[2]).intValue());
                points.add(point);
          
            }
            JSONArray jsonArray = JSONArray.fromObject(points);
            JSONObject json = new JSONObject();
            json.accumulate("result", jsonArray);
            String result = json.toString();
            LogUtil.info("json:" + result);

            // response的响应方式
            PrintWriter out = null;
            // LogUtil.info(result);
            // 设置响应数据类型，这里是json
            response.setContentType("application/json");
            // 设置响应编码格式，防止乱码
            response.setCharacterEncoding("UTF-8");
            try
            {
                out = response.getWriter();
                out.write(result);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                out.close();
            }
        }
    }

}
