package cn.hhit.eshop.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Status;
import cn.hhit.eshop.model.User;
import cn.hhit.eshop.service.ForderService;

@Controller
@RequestMapping("/user/forder")
public class ForderController {

	@Resource
	private ForderService forderService;
	
	@RequestMapping(value ="/check/save.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ModelAndView save(HttpSession session, ForderBean forder) {
		System.out.println(forder);
		
		ForderBean forder2 = (ForderBean) session.getAttribute("forder");

		User user = (User) session.getAttribute("user");
		
		forder2.setUser(user);
		forder2.setStatus(new Status(1));
		forder2.setFname(forder.getFname());
		forder2.setFaddress(forder.getFaddress());
		forder2.setFphone(forder.getFphone());
		forder2.setFpost(forder.getFpost());
		forder2.setFremark(forder.getFremark());
		forder2.setFtotal(forder2.getFtotal());
		forder2.setFdate(new Date(System.currentTimeMillis()));
		
		forderService.saveForderBean(forder2);
		session.setAttribute("oldForder", forder2);
		session.setAttribute("forder", new ForderBean());
		return new ModelAndView("redirect:/send/main/user/bank");
	}

}
