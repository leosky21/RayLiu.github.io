package com.jf.myspringmvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * 自定义注解的核心处理器，负责调用目标业务方法处理用户请求
 * 
 * @author weidong
 */
public class AnnotationHandleServlet extends HttpServlet {
	private static final long serialVersionUID = -187578654629000019L;

	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
			System.out.println("---初始化开始---");
			// 这里需要进行扫描，扫描添加了MyWebServlet注解的类
			String basePackage = config.getInitParameter("basePackage");
			Set<Class<?>> classes = ScanClassUtil.getClasses(basePackage);
			for (Class<?> clazz : classes) {
				// 判断是否包含该注解
				if (clazz.isAnnotationPresent(Controller.class)) {
					// 如果有该注解，那么取到注解的值，存入Map集合中
					Method[] methods = clazz.getDeclaredMethods();
					System.out.println(methods.toString());
					for (Method method : methods) {
						// 判断方法是否添加了注解
						if (method.isAnnotationPresent(RequestMapping.class)) {
							RequestMapping rm = method.getAnnotation(RequestMapping.class);
							String url = rm.value();
							// URL不能重复
							if (!RequestURIMap.isExist(url)) {
								System.out.println(clazz.getName() + "类中扫描到方法：" + method.getName() + "  url 是：" + url);
								RequestURIMap.put(url, clazz);
							} else {
								System.out.println("重复 了");
								throw new RuntimeException("在" + clazz.getName() + "类中发现，" + url + "有重复，请检查代码");
							}
						}
					}
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			this.excute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}



	/**
	 * 解析请求地址，并调用具体的处理请求【方法】
	 */
	private void excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lastUrl = pareRequestURI(request);
		// 获取到对应的类
		Class<?> clazz = RequestURIMap.getUrlMap().get(lastUrl);
		if (clazz == null) {
			throw new RuntimeException("找不到" + lastUrl + "对应的类，请检查配置是否正确");
		}
		// 实例化对应的Servlet类
			Object classInstace = clazz.newInstance();
			Method m = null;// 记录目标方法
			// 请求路径和注解属性值相等，那么就是处理当前请求的方法
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(RequestMapping.class)) {
					String anoPath = method.getAnnotation(RequestMapping.class).value();
					if (anoPath != null && !"".equals(anoPath.trim()) && lastUrl.equals(anoPath.trim())) {
						// 找到要执行的目标方法
						m = method;
						break;
					}
				}
			}
			if (m != null) {
				System.out.println("找到执行的方法了" + m.getName());
				m.setAccessible(true);
				m.invoke(classInstace, request, response);
			} else {
				throw new RuntimeException("在类" + clazz.getName() + "中找不到" + lastUrl + "方法，请检查方法是否存在");
			}
	}

	/**
	 * 解析请求路径，获取到请求的路径，如【http://localhost/MySpringMVC/testServlet】--> 【/testServlet】
	 */
	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath();
		String requestUri = request.getRequestURI();
		String lasturl = requestUri.replaceFirst(path, "");
		lasturl = lasturl.substring(0, lasturl.lastIndexOf("."));
		return lasturl;
	}
}
