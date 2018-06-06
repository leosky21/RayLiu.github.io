package cn.hhit.eshop.controller;

import java.util.HashSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.SorderBean;
import cn.hhit.eshop.service.ForderService;
import cn.hhit.eshop.service.ProductService;
import cn.hhit.eshop.service.SorderService;

@Controller
@Scope("prototype")
@RequestMapping("/user/check/sorder")
public class SorderController {
	@Resource
	private ProductService productService;
	@Resource
	private SorderService sorderService;
	@Resource
	private ForderService forderService;
	
	/**
	 * TODO 3/17 1:08 将加入购物车 改成post 请求,并将订单载入数据库,每次查询从数据库中读取
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addSorder.action", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	public ModelAndView addSorder(
			@RequestParam("id") String id,
			HttpSession session) {  
        
        //1. 根据product.id获取相应的商品数据  
        Product product = productService.get(Integer.parseInt(id));  
          
        //2. 判断当前session是否有购物车，如果没有则创建  
        if(session.getAttribute("forder") == null) {  
            //创建新的购物车Bean，存储到session中 
        	ForderBean bean_forder =  new ForderBean();
        	bean_forder.setSorders(new HashSet<SorderBean>());
//        	Forder bean_forder =  new Forder();
//        	bean_forder.setSorders(new HashSet<Sorder>());
            session.setAttribute("forder", bean_forder);  
        }   
  
        //3. 把商品信息转化为sorder,并且添加到购物车中（判断购物项是否重复）  
        //4. 将商品图片解码转换成baseString
        ForderBean forder = (ForderBean) session.getAttribute("forder");  
        forder = sorderService.addSorderBean(forder, product);  

        //5. 计算购物的总价格  
        forder.setFtotal(forderService.cluTotalBean(forder));  
        //6. 把新的购物车存储到session中  
        session.setAttribute("forder", forder);  
        
        return new ModelAndView("redirect:/send/main/user/shoppingCart");  // TODO 没有空格 和 有空格是有区别
    } 
	
	@RequestMapping(value = "/updateSorder.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
    public double updateSorder(SorderBean sorder, ForderBean forder,HttpSession session) {
        forder = (ForderBean) session.getAttribute("forder");
		for(SorderBean temp : forder.getSorders()) {
            if(temp.getProduct().getId().equals(sorder.getProduct().getId())) {
                temp.setSnumber(sorder.getSnumber());
            }
        }
//        forder = sorderService.updateSerderBean(sorder,forder);
        forder.setFtotal(forderService.cluTotalBean(forder));
        session.setAttribute("forder", forder);
       
        return forder.getFtotal();
    }
	
	
}
