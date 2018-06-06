package cn.hhit.eshop.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.hhit.eshop.model.Account;
import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;
import cn.hhit.eshop.service.AccountService;
import cn.hhit.eshop.service.PersistentLoginsService;
import cn.hhit.eshop.service.UserService;
import cn.hhit.eshop.utils.CookieConstantTable;
import cn.hhit.eshop.utils.CookieUtil;
import cn.hhit.eshop.utils.EncryptionUtil;
import cn.hhit.eshop.utils.MsgUtil;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class AccountController {
	/**
	 * TODO SpringMVC中使用Interceptor+Cookie实现在30天数之内自动登录 TODO
	 * 在SpringMVC中使用拦截器（interceptor）拦截CSRF攻击
	 * 
	 */
	protected Map<String, Object> pageMap = null;
	protected List<Account> jsonList = null;

	@Resource
	private UserService userService;
	@Resource
	private PersistentLoginsService persistentLoginsService;
	@Resource
	private AccountService accountService;

	@RequestMapping(value = "/account/query.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Account> query() {
		jsonList = accountService.query();
		return jsonList;
	}

	@RequestMapping(value = "/admin/register.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminRegister(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("pass") String pass, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		pass = pass.split(",")[0];
		accountService.save(new Account(name, name, pass, null));
		return new ModelAndView("redirect:/send/main/index");
	}

	@RequestMapping(value = "/admin/login.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView adminLogin(HttpServletRequest request, String username, String pass,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		/** 加个防盗链,以辅助注销 退回再次登录 */
		HttpSession session = request.getSession();
		Account admin = new Account();
		Account query_admin = new Account();
		admin.setName(username);
		admin.setPass(pass);
		if (username != null) {
			query_admin = accountService.findAccountByUserName(username);
		}
		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		if (query_admin != null) {

			String pass2 = query_admin.getPass();

			ModelAndView mAndView = null;

			if (pass != null && pass2.equals(pass)) {
				mAndView = new ModelAndView("redirect:/send/main/aindex");
			} else {
				mAndView = new ModelAndView("redirect:/send/main/index");
			}

			session.setAttribute("admin", query_admin); // 登录成功之后加入session中
			redirectAttributes.addFlashAttribute("admin", query_admin);

			return mAndView;
		} else {
			return new ModelAndView("redirect:/send/main/index");
		}
	}

	/**
	 * 注销登录 : logout
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		// 从session中获取用户详情
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			// 删除数据库中的自动登录记录 -----
			request.getSession().setAttribute("Msg", MsgUtil.EXTRA_LOGOUT_MSG); // 登录成功之后加入session中

			return new ModelAndView("redirect:/send/main/login");
		} else {
			PersistentLogins pLogins = persistentLoginsService.selectByUphone(user.getUphone());
			if (pLogins != null)
				persistentLoginsService.delete(pLogins.getId());
			// 清除session和用于自动登录的cookie
			request.getSession().removeAttribute("user");
			CookieUtil.delCookie(request, response, CookieConstantTable.RememberMe);

			return new ModelAndView("redirect:/send/main/login");
		}
	}

	/**
	 * 登录 : login
	 * 
	 * @param username
	 * @param password
	 * @param rememberme
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/login.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView login(@RequestParam("uphone") String uphone, @RequestParam("upass") String upass,
			@RequestParam(value = "remember-me", required = false) boolean rememberme, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {

		/** 加个防盗链,以辅助注销 退回再次登录 */
		HttpSession session = request.getSession();
		User user = new User();
		user.setUphone(uphone);
		user.setUpass(upass);
		// System.out.println("user :: login::" + user.getUname() + "::" +
		// user.getUphone());
		User query_user = this.toLogin(user, rememberme, response);

		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		if (query_user != null) {
			query_user.setUpass("");// 清空pass的缓存

			ModelAndView mAndView = null;
			// 登录之前地址
			String callback = (String) session.getAttribute("callback");
			System.out.println("AccountController :: callback  ::" + callback);
			session.removeAttribute("callback"); // 获取之后移除

			// 基本路径
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();

			// System.out.println("AccountController :: basePath :: " +
			// basePath);
			if (StringUtils.isNotBlank(callback)) {
				String[] urls = callback.split(basePath);
				if (urls.length == 2 && StringUtils.isNotBlank(urls[1])) {
					// mAndView = new ModelAndView("redirect:" + urls[1]);
					mAndView = new ModelAndView("redirect:/send/main/pages/user/index");
				} else {
					mAndView = new ModelAndView("redirect:/send/main/pages/user/index");
				}
			} else {
				mAndView = new ModelAndView("redirect:/send/main/pages/user/index");
			}

			session.setAttribute("user", query_user); // 登录成功之后加入session中
			redirectAttributes.addFlashAttribute("user", query_user);

			return mAndView;
		} else {
			return new ModelAndView("redirect:/send/main/login");
		}

	}

	/**
	 * @param user
	 * @param rememberme
	 * @param response
	 * @return
	 */
	public User toLogin(User user, boolean rememberme, HttpServletResponse response) {
		User query_user = new User();

		/**
		 * a：向表persistent_logins保存记录， Uphone是当前用户登录名； series是获取的当前的UUID值；
		 * token是用户登录手机号、密码、cookie到期时间、以及自定义的salt经过sha256非对称加密之后的字符串；
		 * validTime是到期时间。
		 * 
		 * b：向“remember-me”这个cookie保存的记录值是用户名和UUID值经过base64编码之后的字符串
		 */
		// 如果手机号和密码不为空，执行登录
		if (StringUtils.isNotBlank(user.getUphone()) && StringUtils.isNotBlank(user.getUpass())) {

			query_user = userService.selectByUphoneNick(user);

			System.out.println("query_user  ::  toLogin::" + query_user.getUname() + "::" + query_user.getUphone());
			/**
			 * TODO login 登录名：： 手机号：： 密码
			 */
			// 如果rememberme为true，则保存cookie值，下次自动登录
			if (query_user != null && rememberme == true) {
				// 有效期
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, 1); // 一个月
				Date validTime = calendar.getTime();

				// 精确到分的时间字符串
				String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
						+ calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
						+ calendar.get(Calendar.MINUTE);

				/**
				 * sha256加密用户信息 登录手机号 + 密码
				 */
				String userInfoBySha256 = EncryptionUtil.sha256Hex(query_user.getUphone() + "_" + query_user.getUpass()
						+ "_" + timeString + "_" + CookieConstantTable.salt);
				// UUID值
				String uuidString = UUID.randomUUID().toString();
				// Cookie值
				String cookieValue = EncryptionUtil.base64Encode(query_user.getUphone() + ":" + uuidString);

				// 在数据库中保存自动登录记录（如果已有该用户的记录则更新记录）
				PersistentLogins pLogin = persistentLoginsService.selectByUphone(query_user.getUphone());
				if (pLogin == null) {
					pLogin = new PersistentLogins();
					pLogin.setUphone(query_user.getUphone());// 登录手机号
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(validTime);
					System.out.println(pLogin.getSeries() + "::" + pLogin.getToken());
					persistentLoginsService.save(pLogin);

				} else {
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(validTime);
					persistentLoginsService.update(pLogin);
				}
				// 保存cookie
				CookieUtil.addCookie(response, CookieConstantTable.RememberMe, cookieValue, null);
			}
		}
		return query_user;
	}

}
