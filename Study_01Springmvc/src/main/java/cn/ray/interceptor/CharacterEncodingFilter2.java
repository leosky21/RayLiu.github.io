package cn.ray.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
/**
 * 装饰（Decorator）模式的：
 * 		装饰模式又名包装(Wrapper)模式。
 * 		装饰模式以对客户端透明的方式扩展对象的功能，
 * 		是继承关系的一个替代方案。
 * 		装饰模式是在不必改变原类文件和使用继承的情况下，
 * 		动态的扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
 */
/**
 * Servlet Filter implementation class CharacterEncodingFilter2
 */
@WebFilter(filterName="/CharacterEncodingFilter2",urlPatterns="/*")
public class CharacterEncodingFilter2 implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// pass the request along the filter chain
		chain.doFilter(new MyRequest(req), response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	/*
    1. 写一个类实现与被增强对象相同的接口。
    2. 定义一个变量，记住被增强对象。
    3. 定义一个构造方法，接收被增强对象
    4. 覆盖想增强的方法
    5. 对于不想增强的方法，直接调用被增强对象(目标对象)的方法
    */
	
	class MyRequest extends HttpServletRequestWrapper{
/**
 * Servlet API中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper(HttpServletRequestWrapper类实现request接口中的所有方法，但这些方法的内部实现都是仅仅调用了一下所包装的的request对象的对应方法）以避免用户在对request对象进行增强时需要实现request接口中的所有方法
 */
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		 @Override
	        public String getParameter(String name) {

	            String value = this.request.getParameter(name);
	            if (!this.request.getMethod().equalsIgnoreCase("get")) {
	                return value;
	            }
	            if (value == null) {
	                return null;
	            }
	            try {
	                // return new String(value.getBytes("ISO8859-1"), request.getCharacterEncoding());
	                return new String(value.getBytes("UTF-8"), request.getCharacterEncoding());
	           
	            } catch (UnsupportedEncodingException e) {
	                throw new RuntimeException(e);
	            }
	        }
		
	}
}
