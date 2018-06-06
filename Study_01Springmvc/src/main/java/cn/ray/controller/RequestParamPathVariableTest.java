package cn.ray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//在正常编译情况下，Java类反射对象是不包含方法的入参名称的。只有在debug模式下才会有参数信息。
@Controller
@RequestMapping("/RequestParamPathVariableTest")
public class RequestParamPathVariableTest {
	 
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	    public String test(
	    		@RequestParam(
	    				 value = "name",
	    				 required = false, //是否必需，默认为true，表示请求参数中必须包含该参数，如果不包含抛出异常。
	    				 defaultValue="defalut"
	    		)  String name) {
	        System.out.println(name);
	        return "hello";
	    }
	
	
	/**
	 * 当使用@RequestMapping URI占位符映射时，Url中可以通过一个或多个{xxxx}占位符映射，
	 * 通过@PathVariable可以绑定占位符参数到方法参数中。
	 * @param name
	 * @return
	 */
	 @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
	    public String test2(
	    		@PathVariable("name") String name) {
	        System.out.println(name);
	        return "hello";
	    }
}
