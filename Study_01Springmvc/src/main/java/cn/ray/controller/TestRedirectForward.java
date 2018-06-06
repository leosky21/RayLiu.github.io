package cn.ray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*一般情况下(没有标注@ResponseBody)，控制器方法返回字符串类型的值会被当成逻辑视图名来处理。*/
@Controller
public class TestRedirectForward {

	
	/**
	 * 如果返回的字符串中带有forward:
	 * 			或redirect:前缀时，
	 * 		
	 * 		SpringMVC会对他们进行特殊处理：
	 * 		
	 * 		将forward和redirect当成指示符，其后的字符串作为URL来处理。
	 * @return
	 */
	@RequestMapping("/testredirect")
    public String test()  {
        System.out.println("test");
        return "redirect:/index.jsp";
    }

	
	/**
	 * 当我们访问http://localhost:8080/test/hello
			会转发到/WEB-INF/views/success.jsp。
				这就实现了不通过controller实现页面转发与重定向的操作。
	 * <mvc:view-controller path="/hello"
	 * 		 view-name="forward:/WEB-INF/views/success.jsp"/>
	 */
}
