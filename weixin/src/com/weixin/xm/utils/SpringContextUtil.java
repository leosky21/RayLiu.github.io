package com.weixin.xm.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 *[概要说明：]获取注解中bean
 *[详细说明：]线程中不能直接注入bean,线程中bean都通过此类中获取
 *@author lj 2016-9-27
 * 
 */
public class SpringContextUtil implements ApplicationContextAware
{

	 private static ApplicationContext context = null;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException 
	{
		
		this.context = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName)
	{
        return (T) context.getBean(beanName);
    }

    public static String getMessage(String key)
    {
        return context.getMessage(key, null, Locale.getDefault());
    }
}
