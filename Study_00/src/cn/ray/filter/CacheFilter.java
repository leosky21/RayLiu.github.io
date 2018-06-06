package cn.ray.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ：一个<filter>可以对应多个<filter-mapping>。 
现在加上前面还有一个禁止浏览器缓存所有动态页面的过滤器，
	那么就有2个过滤器了，形成了一个过滤器链。
	
	打开IE浏览器，输入访问服务器的地址http://localhost:8080/Study_00/index.jsp，
	那么在IE浏览器默认的缓存目录里面将不会缓存index.jsp这个页面，
	但会缓存Krystal.jpg这个静态图片，而且缓存一分钟。
	如果在这一分钟之内，通过在IE浏览器的地址栏上敲回车键的行为去访问服务器，
	那么只会向服务器发送一次请求，即请求index.jsp这个动态Web资源，
	至于index.jsp页面中的Krystal.jpg静态图片，
	会从IE浏览器默认的缓存目录里面获取；如果通过点击刷新按钮的行为去访问服务器，
	那么将会向服务器发送二次请求，
		第一次请求index.jsp这个动态Web资源，
		第二次请求index.jsp页面中的Krystal.jpg静态图片
 * @author ray
 *
 */

//@WebFilter(
//		//一个<filter>可以对应多个<filter-mapping>。 
//		filterName="CacheFilter",
//		urlPatterns={"*.jpg","*.png","*.jpeg","*.css","*.js"},
//		initParams={
//				@WebInitParam(name="jpg", value="1"),
//				@WebInitParam(name="png",value="1"),
//				@WebInitParam(name="jpeg", value="1"),
//				@WebInitParam(name="css",value="10"),
//				@WebInitParam(name="js",value="20")
//				}
//		)
public class CacheFilter implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        
        String uri = request.getRequestURI();
        int expires = 0;
        
        if(uri.endsWith(".jpg")){
        	expires = Integer.parseInt(this.config.getInitParameter("jpg"));
        }else if(uri.endsWith(".png")){
        	expires = Integer.parseInt(this.config.getInitParameter("png"));
        }else if(uri.endsWith(".jpeg")){
        	expires = Integer.parseInt(this.config.getInitParameter("jpeg"));
        }else if(uri.endsWith(".css")){
        	expires = Integer.parseInt(this.config.getInitParameter("css"));
        }else if(uri.endsWith(".js")){
        	expires = Integer.parseInt(this.config.getInitParameter("js"));
        }
        
        //Expires相应头的值为GMT时间值，即从1970-01-01 08:00:00到现在时刻的毫秒值。
        //应该在当前时间值上加上一分钟，这是才缓存一分钟。 
        response.setDateHeader("expires", System.currentTimeMillis()+expires*60*1000);
        arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;

	}

}
