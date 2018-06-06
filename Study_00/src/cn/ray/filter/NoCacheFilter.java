package cn.ray.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebFilter(filterName="NoCacheFilter",urlPatterns="*.jsp")
public class NoCacheFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest requset, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)requset;
		HttpServletResponse resp = (HttpServletResponse)response;
		/*禁止浏览器缓存所有动态页面*/
		/*并不是所有的浏览器都能完全支持这三个响应头*/
		resp.setDateHeader("Expires",-1);
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Pragma","no-cache");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
