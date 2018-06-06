package cn.hhit.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hhit.pojo.RequestPojo;

@Controller
@RequestMapping("/send")
public class BaseController {
 
	@RequestMapping("/test.action")
	@ResponseBody
	public RequestPojo test(HttpServletRequest rq,String param){
	
		String ce =  rq.getCharacterEncoding();
		int cl = rq.getContentLength();
		String la = rq.getLocalAddr();
		String ln = rq.getLocalName();
		int lp = rq.getLocalPort();
		String ra = rq.getRemoteAddr();
		int rp= rq.getRemotePort();
		String p = rq.getProtocol();
		String sn = rq.getServerName();
		int sp = rq.getServerPort();
		String ru = rq.getRequestURI();
		String rsi = rq.getRequestedSessionId();
		String rus = rq.getRemoteUser();
		Date date = new Date(System.currentTimeMillis());
			System.out.println(date);
			String da = date+"";
		RequestPojo rpo = new RequestPojo(ce,cl,la,ln,lp,ra,rp,p,sn,sp,ru,rsi,rus,param,da);

		return rpo;
	}
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
