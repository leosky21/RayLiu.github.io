package cn.ray.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.domain.User;
import cn.ray.service.BusinessService;
import cn.ray.utils.MD5Util;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter(filterName = "/AutoLoginFilter", servletNames = "/*")
public class AutoLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AutoLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 1. 先检查用户是否已登录，没登录才自动登录
		User user = (User) req.getSession();
		if (user != null) {
			chain.doFilter(request, response);
		}

		Cookie autoLogin = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("autologin")) {
					autoLogin = c;
					break;
				}
			}
		}
		if (autoLogin == null) {
			chain.doFilter(request, response);
			return;
		}

		//用户带了自动登录的Cookie
		String[] values = autoLogin.getValue().split("\\:");
		if(values.length != 3){
			chain.doFilter(request, response);
			return ;
		}
		
		long expirestime = Long.parseLong(values[1]);
		if(System.currentTimeMillis() >= expirestime){//失效了
			chain.doFilter(request, response);
			return;
		}
		
		String username = values[0];
		String client_MD5 = values[2];
		BusinessService service = new BusinessService();
		user = service.findUser(username);
		if(user==null){
			chain.doFilter(request, response);
		}
		//获取数据库中的用户信息
		String server_md5 = MD5Util.md5Login(user.getUsername(), user.getPassword(), expirestime);
        if (!server_md5.equals(client_MD5)) {//比对,不解码直接比对
            chain.doFilter(request, response);
            return;
        }

        req.getSession().setAttribute("user", user);
		System.out.println("将自动登录-----------");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
