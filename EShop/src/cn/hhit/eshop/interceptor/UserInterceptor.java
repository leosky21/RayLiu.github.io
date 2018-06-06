package cn.hhit.eshop.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;
import cn.hhit.eshop.service.PersistentLoginsService;
import cn.hhit.eshop.service.UserService;
import cn.hhit.eshop.utils.CookieConstantTable;
import cn.hhit.eshop.utils.CookieUtil;
import cn.hhit.eshop.utils.EncryptionUtil;

public class UserInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private UserService userService;
	@Resource
	private PersistentLoginsService persistentLoginsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String callback = "";
		session.setAttribute("callback", callback);
		// 已登录
		if (user != null) {
			return true;
		} else {
			// 从cookie中取值
			Cookie rememberme = CookieUtil.getCookie(request, CookieConstantTable.RememberMe);
			if (rememberme != null) {
				String cookieValue = EncryptionUtil.base64Decode(rememberme.getValue());

				String[] cValues = cookieValue.split(":");
				if (cValues.length == 2) {
					String uphoneByCookie = cValues[0]; // 获取用户名
					String uuidByCookie = cValues[1]; // 获取UUID值
					// 到数据库中查询自动登录记录
					PersistentLogins pLogins = persistentLoginsService
									.selectByUphoneAndUuid(uphoneByCookie,
									uuidByCookie);
					if (pLogins != null) {
						String savedToken = pLogins.getToken(); // 数据库中保存的密文

						// 获取有效时间
						Date savedValidtime = pLogins.getValidTime();
						Date currentTime = new Date();

						// 如果还在cookie有效期之内，继续判断是否可以自动登录
						if (currentTime.before(savedValidtime)) {
							User u = userService.selectByUphone(uphoneByCookie);
							if (u != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(pLogins.getValidTime());
								// 精确到分的时间字符串
								String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)
										+ "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-"
										+ calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE);
								// 为了校验而生成的密文
								String newToken = EncryptionUtil.sha256Hex(u.getUphone() + "_" + u.getUpass() + "_"
										+ timeString + "_" + CookieConstantTable.salt);

								// 校验sha256加密的值，如果不一样则表示用户部分信息已被修改，需要重新登录
								if (savedToken.equals(newToken)) {
									/**
									 * 为了提高安全性，每次登录之后都更新自动登录的cookie值
									 */
									// 更新cookie值
									String uuidNewString = UUID.randomUUID().toString();
									String newCookieValue = EncryptionUtil
											.base64Encode(u.getUphone() + ":" + uuidNewString);
									CookieUtil.editCookie(request, response, CookieConstantTable.RememberMe,
											newCookieValue, null);

									// 更新数据库
									pLogins.setSeries(uuidNewString);
									persistentLoginsService.update(pLogins);

									/**
									 * 将用户加到session中，不退出浏览器时就只需判断session即可
									 */
									session.setAttribute("user", u);

									return true; // 校验成功，此次拦截操作完成
								} else { // 用户部分信息被修改，删除cookie并清空数据库中的记录
									CookieUtil.delCookie(response, rememberme);
									persistentLoginsService.delete(pLogins.getId());
								}
							}
						} else { // 超过保存的有效期，删除cookie并清空数据库中的记录
							CookieUtil.delCookie(response, rememberme);
							persistentLoginsService.delete(pLogins.getId());
						}
					}
				}
			}
			// 将来源地址存放在session中，登录成功之后跳回原地址
			callback = request.getRequestURL().toString();
			session.setAttribute("callback", callback);
			response.sendRedirect(request.getContextPath() + "/send/main/login?callback=" + callback);
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
