package cn.tedu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装和Cookie相关的操作
 */
public class CookieUtil {
	//向响应中增加一个Cookie
	public static void addCookie(
			HttpServletRequest req,
			HttpServletResponse resp,		
			String name, String value, int age) {
		
		try {
			value = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("不支持该编码",e);
		}
		Cookie c = new Cookie(name, value);
		c.setMaxAge(age);
		c.setPath(req.getContextPath());
		resp.addCookie(c);
	}
	
	public static String findCookie(
			HttpServletRequest req,
			String name) {
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for( Cookie c : cookies ) {
				if(c.getName().equals(name)) {
					String value = c.getValue();
					try {
						value = URLDecoder.decode(value,"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw new RuntimeException("不支持该编码",e);
					}
					return value;
				}
			}
		}
		return null;			
	}
	
	//删除用户浏览器中指定名称的Cookie
	public static void deleteCookie(
			HttpServletRequest req,
			HttpServletResponse resp,
			String name) {
		Cookie c = new Cookie(name, "");
		c.setMaxAge(0);
		c.setPath(req.getContextPath());
		resp.addCookie(c);
	}
	
}
