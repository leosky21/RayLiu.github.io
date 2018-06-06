package cn.hhit.eshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


public class WebUtils {
	 // RegisterForm.class
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            // 1.创建要封装数据的bean
            T bean = beanClass.newInstance();

            // 2.把request中的数据整到bean中
            Enumeration<String> e = request.getParameterNames();
            while(e.hasMoreElements()) {
                String name = e.nextElement();               
                String value = request.getParameter(name);   
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            
            throw new RuntimeException(e);
        } 
    }

    public static void copyBean(Object src, Object dest) {
        // 注册日期转换器
        ConvertUtils.register(new Converter() {
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
				if(value == null) {
                    return null;
                }


                String str = (String) value;
                if(str.trim().equals("")) {
                    return null;
                }

                // 1980-09-32
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return  df.parse(str);
                }catch (java.text.ParseException e) {
                    /*
                     * 出现异常，一定要通知上一层程序，抛个异常给上一层，
                     * 不能打印在控制台上。
                     * 且异常链不能断，必须把原来的异常信息封装进去，
                     * 抛出异常给上一层，上一层就会知道到底出了什么问题
                     */
                    throw new RuntimeException(e);
                } 
			}
        }, Date.class);

        try {
            /*
             * 在此项目中，是从formbean将属性拷贝到user对象中去，bean的拷贝
             * 在实际开发中，非常实用
             */
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 产生全球唯一的id
    public static String generateID() {
        return UUID.randomUUID().toString();    // UUID算法根据你系统的网卡的xx地址、CPU、机器的型号等等生成一个128位长的字符串，可以确保是全球唯一的。
    }

}
