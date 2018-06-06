package cn.ray.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/**
 * - TODO  封装客户端提交的表单数据到User对象中
 * @author liujun
 *
 */
public class WebUtils {
	public static <E> E request2Bean(HttpServletRequest req, Class<E> beanClass){
		
		try{
			E bean = beanClass.newInstance();
			
			Map<String,String[]> map = req.getParameterMap();
			
			//注册一个转换器,将 date转换成固定格式
			ConvertUtils.register(new Converter() {
				
				@Override
				public Object convert(Class type, Object value) {
					if(value==null){
						return null;
					}
					String str = (String) value;
					if(str.trim().equals("")){
						return null;
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try{
						return  df.parse(str);
					}catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}

				
			}, Date.class);
			
			
			BeanUtils.populate(bean, map);// map{name=aa,password=abc,birthday=1990-10-09}     bean(name=aa,password=abc,birthday=Date)         
            return bean;
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void copyBean(Object src, Object dest) {
        // 注册日期转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
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
                   
                    throw new RuntimeException(e);
                } 
            }
        }, Date.class);

        try {
            
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	//生成全球唯一的id
	public static String generateID(){
		
		return UUID.randomUUID().toString();
	}
}
