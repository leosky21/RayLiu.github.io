/**
 * /** 金鸽公司源代码，版权归金鸽公司所有。<br>
 * 项目名称 : weixin
 */

package com.weibo.jg.collector;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.id.Hex;
import org.apache.commons.id.uuid.UUID;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.weibo.jg.bean.Wbinfor;
import com.weibo.jg.wb.SelectDao;
import com.weixin.xm.utils.LogUtil;
import com.weixin.xm.utils.SpringContextUtil;

/**  
 * 概要说明 : 新浪微博地域信息采集 <br>
 * 详细说明 :  <br>
 * 创建时间 : 2018年3月9日 下午5:18:00 <br>
 * @author  by lj  
 */

public class SinaWeiboCollector
{
    public static CookieStore cookieStore = new BasicCookieStore();;

    public static HttpContext httpContext = new BasicHttpContext();;

    public static String codeCookie = null;

    private static String[] agents = { "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 OPR/26.0.1656.60",
            "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122",
            "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (Linux; U; Android 2.2.1; zh-cn; HTC_Wildfire_A3333 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1" };

    private SelectDao selectDao;

    private final static SinaWeiboCollector sinaWeibo = new SinaWeiboCollector();

    private SinaWeiboCollector()
    {
        if (selectDao == null)
        {
            selectDao = SpringContextUtil.getBean("selectDao");
        }
    }

    public static SinaWeiboCollector getInstance()
    {
        return sinaWeibo;
    }

    public void execute()
    {

        String url = "https://weibo.com/p/1001018008632070000000000";// 连云港
        String url1 = "https://weibo.com/p/1001018008632010000000000";// 南京
        String url2 = "https://weibo.com/p/1001018008611000000000000";// 北京
        String url3 = "https://weibo.com/p/1001018008631000000000000";// 上海
        String url4 = "https://weibo.com/p/1001018008644030000000000";// 深圳
        // String hotUrl="https://weibo.com/p/1001018008632010000000000/relateweibo?from=page_100101&mod=TAB#place";//此地热议地址
        // LogUtil.info("请求地址："+hotUrl);
        // cookie值
        String cookie = CollectConfigUtil.getValue("weiboCookie");
        String[] urlArr = { url, url1, url2, url3, url4 };
        String[] cityArr = { "连云港", "南京", "北京", "上海", "深圳" };
        for (int i = 0; i < urlArr.length && i < cityArr.length; i++)
        {
            this.collector(urlArr[i], cookie, cityArr[i]);
            try
            {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    /**
     * 定向采集
     * 
     * @param dirUrl
     */
    public void collector(String dirUrl, String cookie, String city)
    {
        int pageBar = 0;
        String nextPageUrl = null;
        String domain = null;
        String id = null;
        String html = null;
        while (pageBar <= 2)
        {
            if (StringUtils.isNotBlank(nextPageUrl))
            {
                dirUrl = nextPageUrl;
            }
            html = weiboRequest(dirUrl, cookie);
            if (StringUtils.isBlank(html))
            {
                LogUtil.info("新浪微博html代码为空！");
                return;
            }
            // 信息提取
            this.getInfor(html, city);
            // 拼接下一页地址
            if (StringUtils.isBlank(domain) && StringUtils.isBlank(id))
            {
                domain = StringUtilsTool.extract(html, "CONFIG\\['domain'\\]='", "'").trim();
                id = StringUtilsTool.extract(html, "CONFIG\\['page_id'\\]='", "'").trim();
            }
            if (StringUtils.isNotBlank(domain) && StringUtils.isNotBlank(id))
            {
                nextPageUrl = "https://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=" + domain + "&current_page=1&since_id=&page=1&pagebar=" + pageBar + "&tab=home&pl_name=Pl_Third_App__17&id="
                        + id + "&script_uri=/p/" + id + "/home&feed_type=1&pre_page=1&domain_op=" + domain + "&__rnd=" + System.currentTimeMillis();
                LogUtil.info("微博定向下一页地址:" + nextPageUrl);
                pageBar++;
            }
            else
            {
                LogUtil.info("微博定向下一页请求参数提取失败，请核查！");
                break;
            }

            try
            {
                TimeUnit.SECONDS.sleep(2);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();

            }
        }
    }

    /**
     * 微博请求
     * @param url 地址
     * @return
     * 1522248656@139.com   a5734011
     */
    public static String weiboRequest(String url, String cookie)
    {
        // cookie值
        if (StringUtils.isBlank(cookie))
        {
            LogUtil.info("新浪微博参数获取错误!!");
            return "";
        }
        String html = null;
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        if (null != cookieStore)
        {
            cookieStore.clear();
        }
        // 创建get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie", "SUB=" + cookie);
        int index = RandomUtils.nextInt(agents.length - 1);
        String agent = agents[index];
        httpGet.setHeader("User-Agent", agent);
        httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try
        {
            // 执行请求
            httpResponse = httpClient.execute(httpGet, httpContext);
            // 获取响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            html = EntityUtils.toString(httpEntity, "utf-8");
        }
        catch (Exception e)
        {
            LogUtil.info("微博请求出现异常：" + e.toString());
            return null;
        }
        return html;
    }
//select *  from wbinfor where   wbinfor.publicTime>='2018-04-08 24:00' and wbinfor.publicTime <='2018-04-13 24:00' and wbinfor.city= '连云港' group by  uid;
    /**
     * 新浪微博信息提取
     * @param html
     */
    public void getInfor(String html, String city)
    {
        List<String> items = StringUtilsTool.getBetween(html, "<div class=[\\S]{1}\"WB_feed_detail clearfix[\\s\\S]*?>", "<div node-type=[\\S]{2}feed_list_repeat[\\s\\S]*?>", false);
       
        for (String item : items)
        {
            Wbinfor infor = new Wbinfor();
            //infor.setId(new String(Hex.encodeHex(UUID.randomUUID().getRawBytes())));
            infor.setCity(city);
            // 内容
            String content = StringUtilsTool.extract(item, "<div class=[\\S]{1}\"WB_text W_f14[\\s\\S]*?>", "<i class=[\\S]{1}\"W_ficon ficon_cd_place[\\S]{1}\">");
            content = content.replace("\\n", "");
            content = content.trim();
            content = StringUtilsTool.unicodeconvertchinese(content);
            // 转发微博内容
            String content1 = StringUtilsTool.extract(item, "<div class=[\\S]{1}\"WB_text[\\S]{2}[\\s\\S]*?>", "<[\\S]{1}/div>");
            content1 = content1.replace("\\n", "").trim();
            if (StringUtils.isNotBlank(content1))
            {
                content1 = StringUtilsTool.unicodeconvertchinese(content1);
                content = content + content1.trim();
            }
            if (StringUtils.isBlank(content))
            {
                continue;
            }
            infor.setInfortext(content);
            String place = StringUtilsTool.extract(item, "<i class=[\\S]{1}\"W_ficon ficon_cd_place[\\S]{1}\">[\\s\\S]*?<[\\S]{1}/i>", "<[\\S]{1}/a>");
            place = StringUtilsTool.unicodeconvertchinese(place);
            infor.setPlace(place);
            LogUtil.info("位置：" + place);
            // 获取经纬度值
            if (StringUtils.isNotBlank(place))
            {
                GetLatAndLngByBaidu getLatAndLngByBaidu = new GetLatAndLngByBaidu();
                Object[] o;
                try
                {
                    String p = place.replace(".", "");
                    o = getLatAndLngByBaidu.getCoordinate(p);
                    if (o != null && o.length > 1)
                    {
                        String lon = o[0].toString();// 经度值
                        String lat = o[1].toString();// 纬度值
                        infor.setLon(lon);
                        infor.setLat(lat);
                        // LogUtil.info(place+"的经度值:"+o[0]+" 纬度值："+o[1]);// 经度
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
            }

            // 地址
            String url = StringUtilsTool.extract(item, "<div class=[\\S]{2}WB_from S_txt2[\\S]{2}>[\\s\\S]*?<a[\\s\\S]*?href=[\\S]{2}", "\"");
            if (StringUtils.isNotBlank(url) && !url.startsWith("http"))
            {
                url = "http://weibo.com" + url;
            }
            url = url.replace("\\", "");
            //查询是否已经采集过了
            Wbinfor wb=selectDao.getWbinforByUrl(url);
            if(wb!=null)
            {
                LogUtil.info("此信息已经采集过啦！");
                continue;
            }
            LogUtil.info("地址：" + url);
            infor.setUrl(url);
            // 发布时间
            String publishTime = StringUtilsTool.extract(item, "<div class=[\\S]{2}WB_from S_txt2[\\S]{2}>[\\s\\S]*?<a[\\s\\S]*?title=[\\S]{2}", "\"");
            LogUtil.info("时间：" + publishTime.replace("\\", ""));
            infor.setPublicTime(publishTime.replace("\\", ""));
            //提取用户ID
            String uid=StringUtilsTool.extract(item, "<a target=[\\S]{1}\"_blank[\\S]{1}\" class=[\\S]{1}\"W_face_radius[\\s\\S]*?href=[\\S]{1}\"[\\S]{1}/[\\S]{1}/weibo.com[\\S]{1}/", "\\?");
            if(StringUtils.isBlank(uid))
            {
                continue;
            }
            if(uid.startsWith("u\\/"))
            {
                uid=uid.substring(uid.indexOf("/")+1, uid.length());
            }      
            infor.setUid(uid);
            LogUtil.info("用户ID：" + uid);
            //转发数
            String reNum=StringUtilsTool.extract(item, "<span class=[\\S]{1}\"line S_line1[\\S]{1}\" node-type=[\\S]{1}\"forward_btn_text[\\S]{1}\">[\\s\\S]*?<[\\S]{1}/em><em>", "<[\\S]{1}/em>");
            if(StringUtils.isNotBlank(reNum))
            {
                try
                {
                    infor.setReNum(Integer.parseInt(reNum.trim()));
                }catch(Exception e)
                {
                    infor.setReNum(0);
                }
            }else
            {
                infor.setReNum(0); 
            }
            LogUtil.info("转发数：" + reNum.trim());
            //评论数
            String commentNum=StringUtilsTool.extract(item, "<span class=[\\S]{1}\"line S_line1[\\S]{1}\" node-type=[\\S]{1}\"comment_btn_text[\\S]{1}\">[\\s\\S]*?<[\\S]{1}/em><em>", "<[\\S]{1}/em>");
            if(StringUtils.isNotBlank(commentNum))
            {
                try
                {
                    infor.setCoNum(Integer.parseInt(commentNum.trim()));
                }catch(Exception e)
                {
                    infor.setCoNum(0);
                }
            }
            else
            {
                infor.setCoNum(0); 
            }
            LogUtil.info("评论数：" + commentNum.trim());
            //点赞数
            String liNum=StringUtilsTool.extract(item, "<span node-type=[\\S]{1}\"like_status[\\S]{1}\"[\\s]*class=[\\S]{1}\"[\\S]{1}\">[\\s\\S]*?<[\\S]{1}/em><em>", "<[\\S]{1}/em>");
            if(StringUtils.isNotBlank(liNum))
            {
                try
                {
                    infor.setLiNum(Integer.parseInt(liNum.trim()));
                }catch(Exception e)
                {
                    infor.setLiNum(0);
                }
            }
            else
            {
                infor.setLiNum(0); 
            }
            LogUtil.info("点赞数：" + liNum.trim());
            // 保存入库
            try
            {
                selectDao.save(infor);
            }
            catch (Exception e)
            {
                LogUtil.info("保存异常：" + e.toString());
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {

        new SinaWeiboCollector().execute();
    }
}
