/**  

/**  
 * 金鸽公司源代码，版权归金鸽公司所有。<br>
 * 项目名称 : monitor
 */  
  
package com.weibo.jg.collector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**  
 * 概要说明 : 采集配置文件工具类  <br>
 * 详细说明 :   <br>
 * 创建时间 : 2018年3月19日 下午2:58:43 <br>
 * @author  by lj  
 */
public class CollectConfigUtil
{
    public CollectConfigUtil(){}
    /**属性文件对象*/
    private static Properties props = new Properties(); 
    static{
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("systemConfig.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getValue(String key){
        return props.getProperty(key);
    }

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
  
