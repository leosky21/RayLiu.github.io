package cn.hhit.eshop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hhit.eshop.model.SorderBean;
import cn.hhit.eshop.service.SorderService;

@Controller
@Scope("prototype")
@RequestMapping("/sale")
public class SaleController {
	
	@Resource
	private SorderService sorderService;
	
	/**
	 * 
	 * error:
	 * 		 Expression #1 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'userinfo.
	 * <https://blog.csdn.net/fansili/article/details/78664267>
	 * 	<	set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';>
	 * 销售管理页面： 实现数据统计信息
	 * @param sorder
	 * @return
	 */
	@RequestMapping(value = "/querySale.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
    public List<Object> querySale(SorderBean sorder,@RequestParam("number")int number) {
		System.out.println(number);
		List<Object> jsonList = sorderService.querySale(number);
        return jsonList;
    }
}
