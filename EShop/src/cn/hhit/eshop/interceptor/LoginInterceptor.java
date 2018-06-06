package cn.hhit.eshop.interceptor;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.hhit.eshop.utils.CookieUtil;

/**
 * 用于在登录前验证 _csrf 参数
 * 
 * 		<https://www.zifangsky.cn/671.html>
 * */

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String _csrfByForm = request.getParameter("_csrf"); // 表单中的值
		String _csrfBySession = String.valueOf(session.getAttribute("_csrf")); // session中的值
		session.removeAttribute("_csrf"); // 防止重复提交 &&  csrf攻击

		// 验证是否存在CSRF攻击
		if (StringUtils.isNotBlank(_csrfByForm) && StringUtils.isNotBlank(_csrfBySession)
				&& _csrfByForm.equals(_csrfBySession)) {
			return true;
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(403);
			// 页面友好提示信息
			OutputStream oStream = response.getOutputStream();
			oStream.write(CookieUtil.LOGINMESSAGE.getBytes("UTF-8"));

			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}

}
