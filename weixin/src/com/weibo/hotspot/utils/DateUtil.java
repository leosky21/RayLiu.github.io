/**
 * 
 */
package com.weibo.hotspot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 概要说明 : 时间操作工具类.<br>
 * 详细说明 : <br>
 * 创建时间 : 2018-3-26, 上午8:48:50
 */
public class DateUtil
{
    /**
     * 概要说明 : 时间格式转换.<br>
     * 详细说明 : <br>
     * 
     * @param date
     * @param pattern
     * @return String
     * @see com.weibo.hotspot.utils.DateUtil#format
     */
    public static String format(Date date, String pattern)
    {
        if (date == null || StringUtils.isBlank(pattern))
        {
            return "";
        }
        
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    /**
     * 概要说明 : 计算时间差.<br>
     * 详细说明 : <br>
     * 
     * @param date1
     * @param date2
     * @return int
     * @see com.weibo.hotspot.utils.DateUtil#daysBetween
     */
    public static int daysBetween(String date1, String date2)
    {
        long betweenDays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try
        {
            // 时间格式转换
            cal.setTime(sdf.parse(date1));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date2));
            long time2 = cal.getTimeInMillis();

            // 计算差值（天数）
            if (time2 - time1 > 0)
            {
                betweenDays = (time2 - time1) / (1000 * 3600 * 24);
            }
            else
            {
                betweenDays = (time1 - time2) / (1000 * 3600 * 24);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return Integer.parseInt(String.valueOf(betweenDays));
    }
}
