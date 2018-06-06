package cn.hhit.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.hhit.pojo.PersistentLogins;
import cn.hhit.pojo.User;
import cn.hhit.service.PersistentLoginsService;
import cn.hhit.service.UserService;
import cn.hhit.utils.CookieConstantTable;
import cn.hhit.utils.CookieUtil;
import cn.hhit.utils.EncryptionUtil;
import cn.hhit.utils.Msgutil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private PersistentLoginsService persistentLoginsService;
	
	@RequestMapping(value = "/test.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public User test(@RequestParam("username") String  username) {
		System.out.println("UserController :: "+username);
		User user = new User("nick","name","pass","sex","phone","height","weight","drugallergyhistory","medicalhistory","img");
		return user;
	}
	
	
	private Map<String, Object> pageMap;
/**
 * 分页查询用户数量:  但是需要屏蔽密码,可以更换表结构设计
 * 根据用户手机号 模糊查询
 * 
 * @param user
 * @param page
 * @param rows
 * @return
 * @throws UnsupportedEncodingException
 */
	@RequestMapping(value = "/queryUser.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryJoinCategory(User user, Integer page, Integer rows)
			throws UnsupportedEncodingException {
		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		// 根据关键字和分页的参数查询相应的数据
		List<User> productList = userService.queryUser(user.getPhone(), page, rows);
		
		pageMap.put("rows", productList); // 存储为JSON格式
		// 根据关键字查询总记录数
		Long total = userService.getCount(user.getNick());
		// System.out.println(total);
		pageMap.put("total", total); // 存储为JSON格式
		return pageMap;
	}
	/**
	 * 根据id删除用户(一个或多个用户) :
	 * 			  根据手机号??
	 * 加防盗链
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/deleteByUphone.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String deleteByUphone(@RequestParam("ids") String id) {
		System.out.println(id);
		userService.delete(Integer.parseInt(id));
		// 如果删除成功就会往下执行，我们将"true"以流的形式传给前台
		try {
			return new String("true".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {

			try {
				return new String("error".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException();
			}
		}
	}
	@RequestMapping("/get.action")
	@ResponseBody
	public void get(@RequestParam("id") String id) {
		
		Map<String, Object> map = new HashMap<String, Object>();	
		userService.get(Integer.parseInt("id"));
	}

	@RequestMapping("/query.action")
	public String query(User user) {
		return "user/query";
	}

	@RequestMapping(value = "/save.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void save(User user)  {
		userService.save(user);
	}

	@RequestMapping(value = "/update.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> update(User user) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(user!=null){
			userService.update(user);
			map.put(Msgutil.keyResult.getMSG_Key(), 0);
			
		}else{
			map.put(Msgutil.keyResult.getMSG_Key(), 1);
			map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.InfoIsNull.getMSG_Key());
		}
		return map;
	}	
	/**
	 * 
	 * 注册:register
	 * 
	 * @param user
	 * @return the status of doRegister where key in {'success','error'}
	 */
	@RequestMapping(value = "/register.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doRegister(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (user != null) {
				// 将user 存入数据库
				userService.save(user);
				System.out.println(user.getNick() + "::" + user.getSex());
				map.put(Msgutil.keyResult.getMSG_Key(), 1);

			} else {
				try {
					map.put(Msgutil.keyResult.getMSG_Key(), -1);
					map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.ResgisterNoUser.getInfo());
				} catch (UnsupportedEncodingException e) {
					// TODO 处理user各种错误的可能性异常
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
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
		Map<String, Object> map = new HashMap<String, Object>();
		// 1.从session中获取用户登录状态
		User user_pc = (User) request.getSession().getAttribute("user");

		if (user_pc == null) {
			// PC : 提示不要重复注销 -----
			try {
				request.setAttribute(Msgutil.keyResult.getMSG_Key(), Msgutil.RepeatMsg.getInfo());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/send/main/login");
		} else {
			// 1. 查询数据库中是否存在该条自动登录验证记录
			PersistentLogins pLogins = persistentLoginsService.selectByUphone(user_pc.getPhone());
			// 2. 存在则删除
			if (pLogins != null)
				persistentLoginsService.delete(pLogins.getId());
			// 3. PC端 :清除session和用于自动登录的cookie
			request.getSession().removeAttribute("user");
			CookieUtil.delCookie(request, response, CookieConstantTable.RememberMe);

			return new ModelAndView("redirect:/send/main/login");
		}

	}

	/**
	 * 
	 * 注销登录 : logout
	 * 
	 * @param request
	 * @param response
	 * @param uphone
	 *            用户手机号
	 * @param unick
	 *            用户登录名
	 * @return 注销成功,则 success,否则 为 error
	 */
	@RequestMapping(value = "/m/logout.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> logoutMobile(HttpServletRequest request, HttpServletResponse response, String uphone,
			String unick) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 2.从数据库中查询 user.loginstatus ? 1 : 0
		User user_db = userService.findUserByUphone(uphone);
		try {
			if (user_db == null) {

				// 移动: 返回不要重复注销
				map.put(Msgutil.keyResult.getMSG_Key(), -1);
				map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.RepeatMsg.getInfo());
			} else {
				// 1. 查询数据库中是否存在该条自动登录验证记录
				PersistentLogins pLogins = persistentLoginsService.selectByUphone(user_db.getPhone());
				// 2. 存在则删除
				if (pLogins != null)
					persistentLoginsService.delete(pLogins.getId());
				// 3. 移动端 : 返回 success,值为 true

				map.put(Msgutil.keyResult.getMSG_Key(), 0);

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
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
	public ModelAndView login(@RequestParam("uphone") String uphone,@RequestParam("nick") String nick, @RequestParam("upass") String upass,
			@RequestParam(value = "remember-me", required = false) boolean rememberme, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		/** 加个防盗链,以辅助注销 退回再次登录 */
		HttpSession session = request.getSession();
		User user = new User();
		user.setPhone(uphone);
		user.setPassword(upass);
		user.setNick(nick);;
		User query_user = this.toLogin(user, rememberme, response);

		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		if (query_user != null) {
			query_user.setPassword("");// 清空pass的缓存

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
					mAndView = new ModelAndView("redirect:" + urls[1]);
				} else {
					mAndView = new ModelAndView("redirect:/send/main/login");
				}
			} else {
				mAndView = new ModelAndView("redirect:/send/main/aindex");
			}

			session.setAttribute("user", query_user); // 登录成功之后加入session中
			redirectAttributes.addFlashAttribute("user", query_user);

			return mAndView;
		} else {
			return new ModelAndView("redirect:/send/main/login");
		}

	}

	/**
	 * 登录 : login
	 * 
	 * @param phone
	 *            用户登录手机号
	 * @param password
	 *            用户登录密码
	 * @param rememberme
	 *            移动端必须设置为 true
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 如果验证登录成功,则返回 true,否则返回error 和error 信息 如果是true ,则提交申请 要 plogin的值
	 */
	@RequestMapping(value = "/m/login.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> loginMobile(@RequestParam("phone") String uphone, @RequestParam("password") String upass,
			@RequestParam(value = "remember-me", required = false) boolean rememberme, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = new User();
		user.setPhone(uphone);
		user.setPassword(upass);

		User query_user = this.mtoLogin(user, rememberme, response);
		try {
			if (query_user != null) {
				// 获取自动登录信息
				PersistentLogins pLogins = persistentLoginsService.selectByUphone(query_user.getPhone());

				if (pLogins == null) {

					map.put(Msgutil.keyResult.getMSG_Key(), -1);
					map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.PersistentLoginsLoss.getInfo());
					return map;
				} else {
					// 登录之前地址
					String callback = (String) session.getAttribute("callback");
					session.removeAttribute("callback"); // 获取之后移除
					// 基本路径
					String basePath = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + request.getContextPath();

					map.put(Msgutil.keyResult.getMSG_Key(), 0);

					return map;
				}

			} else {
				try {
					map.put(Msgutil.keyResult.getMSG_Key(), -1);
					map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.LoginNoUser.getInfo());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return map;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// 未知错误
			map.put(Msgutil.keyResult.getMSG_Key(), -1);
			map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.ERROR_MSG.getInfo());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * @param uphone
	 *            用户登录手机号
	 * @param upass
	 *            用户登录密码
	 * @return 确保Login.action 返回为 success 才得到值 PersistentLogins 对象 或者null
	 */
	@RequestMapping(value = "/m/persistentLogin.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PersistentLogins persisLogin(@RequestParam("phone") String uphone, @RequestParam("password") String upass,@RequestParam("nick")String nick) {

		if (uphone == null || upass == null) {
			return null;
		} else {
			User query_user = userService.findUserByUphoneNick(uphone,nick);
			if(query_user.getPassword().equals(upass)){
				return persistentLoginsService.selectByUphoneNick(query_user.getPhone(),query_user.getNick());
			}else{
				return null;
			}
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
		if (StringUtils.isNotBlank(user.getPhone()) && StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(user.getNick())) {

			query_user = userService.selectByUphonePass(user);

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
				String userInfoBySha256 = EncryptionUtil.sha256Hex(query_user.getPhone() + "_" + query_user.getNick()+"_"
						+ query_user.getPassword() + "_" + timeString + "_" + CookieConstantTable.salt);
				// UUID值
				String uuidString = UUID.randomUUID().toString();
				// Cookie留存值 
				String cookieValue = EncryptionUtil.base64Encode(query_user.getPhone()+":" +query_user.getNick()+ ":" + uuidString);

				// 在数据库中保存自动登录记录（如果已有该用户的记录则更新记录）
				PersistentLogins pLogin = persistentLoginsService.selectByUphoneNick(query_user.getPhone(),query_user.getNick());
				if (pLogin == null) {
					pLogin = new PersistentLogins();
					pLogin.setUphone(query_user.getPhone());// 登录手机号
					pLogin.setUnick(query_user.getNick());
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(new Timestamp(validTime.getTime()));
					System.out.println(pLogin.getSeries() + "::" + pLogin.getToken());
					persistentLoginsService.save(pLogin);

				} else {
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(new Timestamp(validTime.getTime()));
					persistentLoginsService.update(pLogin);
				}
				// 保存cookie
				CookieUtil.addCookie(response, CookieConstantTable.RememberMe, cookieValue, null);
			}
		}
		return query_user;
	}

	/**
	 * @param user
	 * @param rememberme
	 * @param response
	 * @return
	 */
	public User mtoLogin(User user, boolean rememberme, HttpServletResponse response) {
		User query_user = new User();

		/**
		 * a：向表persistent_logins保存记录， Uphone是当前用户登录名； series是获取的当前的UUID值；
		 * token是用户登录手机号、密码、cookie到期时间、以及自定义的salt经过sha256非对称加密之后的字符串；
		 * validTime是到期时间。
		 * 
		 * b：向“remember-me”这个cookie保存的记录值是用户名和UUID值经过base64编码之后的字符串
		 */
		// 如果手机号和密码不为空，执行登录
		if (StringUtils.isNotBlank(user.getPhone()) && StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(user.getNick())) {

			query_user = userService.selectByUphonePass(user);

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
				 * sha256加密用户信息 登录手机号 + 登录名(非用户真实姓名) + 密码
				 */
				String userInfoBySha256 = EncryptionUtil.sha256Hex(query_user.getPhone() + "_"
						+query_user.getNick()+"_"+ query_user.getPassword() + "_" + timeString + "_" + CookieConstantTable.salt);
				// UUID值
				String uuidString = UUID.randomUUID().toString();
				PersistentLogins pLogin = persistentLoginsService.selectByUphoneNick(query_user.getPhone(),query_user.getNick());
				if (pLogin == null) {
					pLogin = new PersistentLogins();
					pLogin.setUphone(query_user.getPhone());// 登录手机号
					pLogin.setUnick(query_user.getNick());
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(new Timestamp(validTime.getTime()));
					System.out.println(pLogin.getSeries() + "::" + pLogin.getToken());
					persistentLoginsService.save(pLogin);

				} else {
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidTime(new Timestamp(validTime.getTime()));
					persistentLoginsService.update(pLogin);
				}
			}
		}
		return query_user;
	}
}