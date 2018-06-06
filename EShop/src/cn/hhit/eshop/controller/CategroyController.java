package cn.hhit.eshop.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.service.CategoryService;

@Controller
@Scope("prototype")
@RequestMapping("/category")
public class CategroyController {

	// page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的
	// page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的
	protected Map<String, Object> pageMap = null;

	@Resource
	private CategoryService categoryService;

	@RequestMapping(value = "/queryJoinAccount.action", produces = "application/json;charset=UTF-8", method= RequestMethod.POST)
	@ResponseBody
	// SpringMVC4 需要引入Jackson的三个jar包 core,databind,annotation
	public Map<String, Object> queryJoinAccount(Category category, Integer page, Integer rows) {
		/** TODO 需要将查出来的pass做处理 */
		System.out.println("----queryJoinAccount.action----");
		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		// 根据关键字和分页的参数查询相应的数据。这个方法我们在Service中写过了，当时完成级联查询
		List<Category> categoryList = categoryService.queryJoinAccount(category.getType(), page, rows);
		pageMap.put("rows", categoryList); // 存储为JSON格式，一个key是total,一个key是rows，这里先把rows存放好
		// 根据关键字查询总记录数
		Long total = categoryService.getCount(category.getType()); // 这个方法没写，我们等会儿去Service层完善一下
		if (total == null)
			total = (long) 0;
		// System.out.println("total::"+total);
		pageMap.put("total", total); // 存储为JSON格式，再把total存放好
		
		//System.out.println(pageMap);
		return pageMap;
	}

	@RequestMapping("/deleteByIds.action")
	@ResponseBody
	public String deleteByIds(String ids) {
		String result;
		try {
			System.out.println(ids);
			categoryService.deleteByIds(ids);
			// 如果删除成功就会往下执行，我们将"true"以流的形式传给前台
			result = new String("true".getBytes("UTF-8"));
			return result;
		} catch (UnsupportedEncodingException e) {
			try {
				return new String("error".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException(e);
			}
		} // 将"true"的字节存到流inputStream中
	}

	@RequestMapping(value="/productQuery.action",produces = "application/json;charset=UTF-8", method= RequestMethod.POST)
	@ResponseBody
	public List<Category> productQuery(Category category) {
		System.out.println("----query----");
		List<Category> categoryList = categoryService.queryJoinAccount(category.getType());
		
		System.out.println(categoryService+"::"+categoryList.size());
		return categoryList;
	}
	@RequestMapping("/query.action")
	public String query(Category category) {
		System.out.println("----query----");
		return "category/query";
	}
	@RequestMapping(value="/update.action",produces = "application/json;charset=utf-8")
	public void update(Category category) {
		System.out.println("----update----");
		System.out.println(categoryService);// 由于已经和Spring整合，所以可以拿到这个categoryService了，打印出来就不是null了
		categoryService.update(category); // 新加一条语句，来更新数据库
		//return "category/update";
	}

	@RequestMapping("/save.action")
	public void save(Category category) {
		System.out.println("----save----");
		System.out.println(categoryService);
		categoryService.save(category);
	//	return "category/save";
	}
}
