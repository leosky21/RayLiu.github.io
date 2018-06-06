package cn.ray.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ray.pojo.Items;
import cn.ray.service.ItemService;


/**
 * @RestController 
 * 		is processed 
 * 	if an appropriate HandlerMapping-HandlerAdapter pair is configured 
 * 				such as the RequestMappingHandlerMapping-RequestMappingHandlerAdapter pair 
 * 							which are the default in the MVC Java config and the MVC namespace. 
 * 	In particular @RestController 
 * 		is not supported with the 
 * 		DefaultAnnotationHandlerMapping-AnnotationMethodHandlerAdapter pair 
 * 				both of which are also deprecated.
 * @author ray
 *
 */
@RestController
/*	<mvc:annotation-driven/>  
 * 		不加本语句,则 默认使用DefaultAnnotationHandlerMapping 和 AnnotationMethodHandlerAdapter
 * 		加了则 默认使用:RequestMappingHandlerMapping以及 RequestMappingHandlerAdapter
 * */
public class ItemController {

	@Resource
	ItemService itemService;

	
	
	
	@RequestMapping("/json_test")
	//@ResponseBody
	public Items jsonTest(@RequestBody Items items) {
		return items;
	}

	@RequestMapping("/item_json")
	//@ResponseBody
	public Items itemJson() {
		return new Items(1, "imac", 20000, new Date(), "苹果本很贵");
	}

	// .action可以省略 (请求的url地址)
	@RequestMapping("/itemList")
	public ModelAndView itemList() {
		// 查询商品列表，使用静态数据生成一个商品列表
		List<Items> itemList = new ArrayList<Items>();
		itemList.add(new Items(1, "imac", 20000, new Date(), "苹果本很贵"));
		itemList.add(new Items(2, "imac1", 20000, new Date(), "苹果本很贵"));
		itemList.add(new Items(3, "imac2", 20000, new Date(), "苹果本很贵"));
		itemList.add(new Items(4, "imac3", 20000, new Date(), "苹果本很贵"));
		itemList.add(new Items(5, "imac4", 20000, new Date(), "卧槽，苹果本很贵啦！"));
		// 把商品列表传递给jsp
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", itemList);
		// 设置展示数据的视图，即jsp
		modelAndView.setViewName("/jsp/itemList");
		// 返回结果
		return modelAndView;
	}

	@RequestMapping("/itemList.do")
	public ModelAndView itemList2() {
		// 查询商品列表，使用静态数据生成一个商品列表
		List<Items> itemList = new ArrayList<Items>();
		itemList.add(new Items(1, "imac", 20000, new Date(), "很贵"));
		itemList.add(new Items(2, "imac1", 20000, new Date(), "很贵"));
		itemList.add(new Items(3, "imac2", 20000, new Date(), "苹果很贵"));
		itemList.add(new Items(4, "imac3", 20000, new Date(), "苹果很贵"));
		itemList.add(new Items(5, "imac4", 20000, new Date(), "卧槽，苹果本很贵啦！"));
		// 把商品列表传递给jsp
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", itemList);
		// 设置展示数据的视图，即jsp
		modelAndView.setViewName("/jsp/itemList");
		// 返回结果
		return modelAndView;
	}

	// SpringMVC框架会自动把Request对象传递给方法。这就是SpringMVC框架默认支持的参数类型。

	public ModelAndView editItem(HttpServletRequest request) {
		// 从request中取出参数
		String strId = request.getParameter("id");
		int id = new Integer(strId);
		// 调用服务
		Items items = itemService.getItemById(id);
		// 把结果传递给页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", items);
		// 设置逻辑视图
		modelAndView.setViewName("editItem");
		return modelAndView;
	}

	
	/** 
     * 1、使用RequestMapping 注解来映射请求的URL 
     * 2、返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器， 
     * 会做如下的解析：通过 prefix + returnValue + suffix 这样的方式得到实际的物理视图，然后做转发操作。 
     * @return 
     */  
	@RequestMapping("/itemEdit")
	public String editItem(Integer id, Model model) {
		// 调用服务
		Items items = itemService.getItemById(id);
		// 把数据传递给页面，需要用到Model接口
		model.addAttribute("item", items);
		// 返回逻辑视图
		return "editItem";
	}
}
