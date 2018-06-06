package cn.ray.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
  * 使用注解标注过滤器
  * @WebFilter 将一个实现了javax.servlet.Filte接口的类定义为过滤器
  * 属性filterName声明过滤器的名称,可选
  * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
  * 
  * @WebFilter(filterName="name",urlPatterns="/*")
 */
/** 
 * - web.xml配置
 * 	<!--注册Filter-->
		<filter>
		    <filter-name>FilterTest1</filter-name>
		    <filter-class> cn.itcast.web.filter.FilterTest1</filter-class>
		</filter>
		
		<!--映射过滤器-->
		<filter-mapping>
		    <filter-name>FilterTest1</filter-name>
		    <!-- “/index.jsp”表示拦截对index.jsp页面的请求 -->
		    <url-pattern>/index.jsp</url-pattern>
		</filter-mapping>
		
		<dispatcher>指定过滤器所拦截的资源被Servlet容器调用的方式，
		可以是REQUEST、INCLUDE、FORWARD和ERROR之一，
		默认REQUEST。
		用户可以设置多个<dispatcher>子元素用来指定Filter对资源的多种调用方式进行拦截。如下：
		
		REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么该过滤器就不会被调用。
		INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
		FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
		ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。
 * @author ray
 *
 */

//@WebFilter(filterName="FilterTest1",urlPatterns="/index.jsp")
public class TestFileter01  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy！！FilterTest1执行！");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("FilterTest1执行之前！！！");
        // 拦截下目标资源，然后放行(目标资源也会执行)
        arg2.doFilter(arg0, arg1); // 放行
        System.out.println("FilterTest1执行之后！！！");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init！！FilterTest1执行！");
	}
/**
 * 
		 * Filter接口中有一个doFilter方法，当我们编写好Filter，
		 * 	并配置对哪个web资源进行拦截后，WEB服务器每次在调用web资源的service方法之前，
		 * 	都会先调用一下filter的doFilter方法
 
 * 	调用目标资源之前，让一段代码执行。
		是否调用目标资源（即是否让用户访问web资源） 
		web服务器在调用doFilter方法时，会传递一个filterChain对象进来，
		filterChain对象是filter接口中最重要的一个对象，它也提供了一个doFilter方法，
		开发人员可以根据需求决定是否调用此方法，调用该方法，
		则web服务器就会调用web资源的service方法，即web资源就会被访问，否则web资源不会被访问。
		调用目标资源之后，让一段代码执行。
 * 
 */
	/**
	 * 	- Filter对象何时被创建？ 
			服务器一启动的时候，就会针对这个web应用将所有的Filter对象(拦截器)创建出来，并且以后访问的时候，都是使用同一个拦截器进行拦截。也即一个拦截器会被所有的请求所共享，每一次请求来了之后，都会导致doFilter()方法被调用一次，Filter对象只有一个，而doFilter()方法会被多次调用。 
		- 问：Filter对象在内存里面有几个？ 
			答：一个。服务器并不会针对请求创建新的Filter对象(拦截器)。
		- Filter对象何时被摧毁？ 
			移除掉web服务器里面这个web应用(或停掉服务器)，就会摧毁这个web应用对应的拦截器。
	 */
}
