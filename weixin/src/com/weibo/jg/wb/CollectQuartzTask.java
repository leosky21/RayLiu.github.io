package com.weibo.jg.wb;

import org.springframework.stereotype.Component;

import com.weibo.jg.collector.SinaWeiboCollector;
import com.weixin.xm.utils.LogUtil;

/**  
 * 概要说明 : 采集定时任务 <br>
 * 详细说明 :   <br>
 * 创建时间 : 2018年3月20日 下午2:59:29 <br>
 * @author  by lj  
 */
@Component
public class CollectQuartzTask
{
    
    public void run()
    {
        LogUtil.info("采集任务开始执行！");
        SinaWeiboCollector.getInstance().execute();
        LogUtil.info("采集任务结束！");
    }

}
  
