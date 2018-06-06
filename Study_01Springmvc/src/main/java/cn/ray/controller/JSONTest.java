package cn.ray.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ray.pojo.Address;
import cn.ray.pojo.User;

@Controller
public class JSONTest {
	//JSON默认的MIME类型为application/json
	 	@RequestMapping("/ajax2")
	    @ResponseBody
	    public List<User> ajax2() {
	        List<User> list = new ArrayList<>();
	        Address address = new Address("shandong", "liaocheng");
	        User user = new User("熙雅", "*****", "*****@qq.com", 24, address);
	        list.add(user);
	        
	        return list;
	    }
}
