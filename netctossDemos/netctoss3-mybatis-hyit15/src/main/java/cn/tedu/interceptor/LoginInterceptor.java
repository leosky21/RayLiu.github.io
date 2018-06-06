package cn.tedu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tedu.entity.Admin;

/**
 * 登录拦截器，用于在访问Controller之前判断是否登录，
 * 若登录，继续向后访问Controller；
 * 否则，拒绝向后访问，强制跳转到登录页面
 * 拦截目标路径：
 * 	- /login/toIndex.do
 * 	- 登录模块之外所有的Controller
 * 即：
 * 	不需要拦截：
 * 		- /login/toLogin.do
 * 		- /login/checkLogin.do
 * 	需要拦截：
 * 		- /login/toIndex.do
 * 		- /cost/find.do
 * 		- ...
 */
public class LoginInterceptor 
			implements HandlerInterceptor{

	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {

		HttpSession session = request.getSession();
		Admin admin =
			(Admin)session.getAttribute("admin");
		if( admin==null ) {
			//没登录，重定向到登录页面
			String contextPath = request.getContextPath();
			response.sendRedirect(
				contextPath + "/login/toLogin.do");
			return false;
		} else {
			//已经登录了，可正常继续向后访问Controller
			return true;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
