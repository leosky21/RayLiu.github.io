package cn.ray.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
		<mvc:interceptors>
		  <mvc:interceptor>
		    <mvc:mapping path="/**" />
		    <bean class="org.format.demo.interceptor.LoginInterceptor"/>
		  </mvc:interceptor>
		</mvc:interceptors>
 * 
 * @author liujun
 *
 */
@Controller
@RequestMapping(value = "/login")
public class InterceptorTest {
	@RequestMapping(value = { "/", "" })
	public String index() {
		return "forward:/index.jsp";
	}

	@RequestMapping("/auth")
	public String auth(@RequestParam String username, HttpServletRequest req) {
		req.getSession().setAttribute("loginUser", username);
		return "redirect:/jsp/itemList";
	}

	@RequestMapping("/out")
	public String out(HttpServletRequest req) {
		req.getSession().removeAttribute("loginUser");
		return "redirect:jsp/itemList";
	} 
}
