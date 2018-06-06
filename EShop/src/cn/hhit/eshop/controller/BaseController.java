package cn.hhit.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/send")
public class BaseController {

//	@Resource
//	protected AccountService accountService;
//	@Resource
//	protected CategoryService categoryService;
//	@Resource
//	protected ProductService productService;
//	@Resource  
//    protected ForderService forderService;  
//    @Resource  
//    protected SorderService sorderService;  
//    
    
	@RequestMapping("/main/{pa}")
	public String send(
			@PathVariable("pa") String pa
			){
		return "main/"+pa;
	}
	@RequestMapping("/main/{fa}/{pa}")
	public String send(
			@PathVariable("fa") String fa,
			@PathVariable("pa") String pa
			){
		System.out.println( "forward: /"+fa+"/"+pa);
		return fa+"/"+pa;
	}
	
	@RequestMapping("/main/{fa}/{pa}/{da}")
	public String send(
			@PathVariable("fa") String fa,
			@PathVariable("pa") String pa,
			@PathVariable("da") String da
			){
		System.out.println( "forward: /"+fa+"/"+pa);
		return fa+"/"+pa+"/"+da;
	}
}
