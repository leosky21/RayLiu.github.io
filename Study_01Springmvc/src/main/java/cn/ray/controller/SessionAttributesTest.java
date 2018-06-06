package cn.ray.controller;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.ray.pojo.User;

/**
 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外(value={"", ""})，
 * 还可以通过模型属性的对象类型指定哪些模型属性需要
			放到会话中(type={String.class, int.class})
 * 
 * @author ray
 *
 */
@Controller
@RequestMapping("/SessionAttributesTest")
@SessionAttributes(value = { "person" },types={User.class})
//@SessionAttributes(value = { "person" },types={int.class})//test2 会报错Expected session attribute 'person'
public class SessionAttributesTest {
	
	
	@RequestMapping(value = "/test")
	public String test(Model model) {
		User person = new User("lgh", null, null, 24, null);
		model.addAttribute("person", person);
		System.out.println("test");
		return "jsp/success";
	}
	/**
	 *  <h1>requestScope.person:${requestScope.person}</h1>  
    	<h1>sessionScope.person:${sessionScope.person}</h1>
    	
    	当我们没有标注@SessionAttributes注解的时候，只会输出：
			requestScope.person:Person{name='lgh', age=24}
			sessionScope.person:
		标注@SessionAttributes才会显示：
			
			requestScope.person:Person{name='lgh', age=24}
			sessionScope.person:Person{name='lgh', age=24}
	 */
	
	
	@RequestMapping(
			params={"name != lgh", "age=24"},
			value = "/test2",
			consumes = "text/html",
			produces = "application/json"
				)
/**
 * Http报头Accept与Content-Type的区别:

		1.Accept属于请求头， Content-Type属于实体头。 
		Http报头分为通用报头，请求报头，响应报头和实体报头。 
		请求方的http报头结构：通用报头|请求报头|实体报头 
		响应方的http报头结构：通用报头|响应报头|实体报头
		
		2.Accept代表发送端（客户端）希望接受的数据类型。 
		比如：Accept: text/xml 
		代表客户端希望接受的数据类型是xml类型
		
		Content-Type代表发送端（客户端|服务器）发送的实体数据的数据类型。 
		比如：Content-Type: text/html 
		代表发送端发送的数据格式是html。
		
		二者合起来， 
		Accept: text/xml 
		Content-Type: text/html 
		即代表希望接受的数据类型是xml格式，本次请求发送的数据的数据格式是html。
 */
	/**
	 * produces
		String[] produces() default {}; 
		表示接收request请求头中的(Accept)字段中包含该指定类型的请求。
	 */
	/**
	 * consumes
		String[] consumes() default {}; 
		表示接收何种内容类型（Content-Type）的请求，例如application/json, text/html。
	 */
	/**
	 * headers
		String[] headers() default {}; 
		表示接收何种请求头的请求。 
		使用和param 参数类似。
	 */
	/**
	 * params
			String[] params() default {}; 
			表示接收何种请求参数的请求。 
			- param1：表示请求必须包含名为 param1 的请求参数 
			- !param1：表示请求不能包含名为 param1 的请求参数 
			- param1!=value1：表示请求包含名为 param1 的请求参数，但是其值不能为 value1；或者，请求不包含名为 param1 的请求参数 
			- {“param1=value1”, “param2”}：表示请求必须包含名为 param1 和 param2 的两个请求参数，且 param1 请求参数的值必须为 value1
	 * @param out
	 * @param person
	 * @throws IOException
	 */
    public void test2(Writer out, @ModelAttribute("person") User person) throws IOException {
        if (person != null) {
            out.write(person.toString());
        } else {
            out.write("person is null");
        }
        out.close();
    }
	
	
}
