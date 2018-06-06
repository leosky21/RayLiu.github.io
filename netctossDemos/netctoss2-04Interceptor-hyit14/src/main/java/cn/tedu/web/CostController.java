package cn.tedu.web;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.entity.Cost;
import cn.tedu.service.CostService;

@Controller
@RequestMapping("/cost")
public class CostController implements Serializable{
	
	@Autowired
	private CostService costService;
	
	@RequestMapping("/find.do")
	public String find(Model model) {
		List<Cost> costs = costService.find();
		model.addAttribute("costs", costs);
		return "cost/find";
	}
}







