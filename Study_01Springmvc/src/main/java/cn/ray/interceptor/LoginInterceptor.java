package cn.ray.interceptor;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
///   https://www.zifangsky.cn/700.html   SpringMVC中使用Interceptor+Cookie实现在一定天数之内自动登录
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获得请求路径的uri
        String uri = request.getRequestURI();//  项目名/路径path1/path2/path3
        System.out.println(uri+"-------------------------"+request.getContextPath() );
        
        // uri[0]  是空
        String[] values = uri.split(File.separator); 
        System.out.println(values[0]+"--------");//   "--------"
        // toLogin 
        for(String value : values){
        	System.out.print(value+"::");//  ::项目名::路径path1::path2::path3
        }
        
        
        // 进入登录页面，判断session中是否有key，有的话重定向到首页，否则进入登录界面
        if(uri.endsWith("/login/") || uri.endsWith("/login")) {
            if(request.getSession() != null && request.getSession().getAttribute("loginUser") != null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                return true;
            }
        }

        // 其他情况判断session中是否有key，有的话继续用户的操作
        if(request.getSession() != null && request.getSession().getAttribute("loginUser") != null) {
            return true;
        }

        // 最后的情况就是进入登录页面
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
	}
	
	

}
