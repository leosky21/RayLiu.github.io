package cn.ray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ray.pojo.User;


/**

1. 由 @ModelAttribute 标记的方法, 会在每个目标方法执行之前被 SpringMVC 调用! 
2. @ModelAttribute 注解也可以来修饰目标方法 POJO 类型的入参, 其 value 属性值有如下的作用:
	1). SpringMVC 会使用 value 属性值在 implicitModel 中查找对应的对象, 若存在则会直接传入到目标方法的入参中.
	2). SpringMVC 会以 @ModelAttribute 的 value 为 key, POJO 类型的对象为 value, 存入到 request 中. 
 * @author liujun
 *
 */
@Controller
@RequestMapping("/ModelAttributeTest")
public class ModelAttributeTest {
	
	@RequestMapping(value = "/hello")  
    public String helloWorld()  {  
        System.out.println("hello World");  
        return "hello";  
    }  
  
  
	/**当我们访问/test时，会先调用preMethod方法，
	 *将name属性值(http://localhost:8080/test/test?name=lgh)放在model的name属性中，
	 *在视图页面中可以直接访问。
	 */
	
    @ModelAttribute  
    public void preMethod(@RequestParam(value = "name", required = false) String name, Model model) {  
        System.out.println("preMethod");  
        model.addAttribute("name", name);  
    }  
  //<h1>${requestScope.name}</h1>
    
    /**
     * 首先访问preMethod方法，返回Person对象
     * 	（实际上把Person对象封装到ModelAndView（实际的返回类型）中），
     * 	model属性的名称没有指定，它由返回类型隐含表示，如这个方法返回Person类型，
     * 	那么这个model属性的名称是person。
     * 也可以指定 名称,比如下面value="person";
     * @return
     */
    @ModelAttribute  
    //@ModelAttribute(value="person")
    public User preMethod() {  
        System.out.println("preMethod");  
        User person = new User("ray", "****", null, 24, null);  
        return person;  
    }  
   // <h1>${persons.name}</h1>  
   // <h1>${persons.age}</h1>  
    
    
    @RequestMapping("/test")  
    public String test() {  
        System.out.println("test");  
        return "success";  
    }  
}

/**
* 运行流程:
* 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: person
* 2. SpringMVC 从 Map 中取出 Person对象, 并把表单的请求参数赋给该 Person对象的对应属性.
* 3. SpringMVC 把上述对象传入目标方法的参数. 
* 
* 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
* 
* SpringMVC 确定目标方法 POJO 类型入参的过程
* 
* 1. 确定一个 key:
	* 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
	* 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值. 
* 
* 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
	* 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到. 
* 
* 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰, 
* 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
* 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常. 
*
* 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
* 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
* 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中. 
* 
* 源代码分析的流程
* 
* 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
* 
* 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
	* 1). 创建 WebDataBinder 对象:
			* ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写. 
				* *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute 
				* 的 value 属性值 
			* 
			* ②. 确定 target 属性:
				* > 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
				* > *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
				* 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常. 
				* > 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
				* 和 attrName 相匹配, 则通过反射创建了 POJO 对象
			* 
	* 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性. 
	* 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel. 
	* 近而传到 request 域对象中. 
	* 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参. 
*/
