package cn.hhit.eshop.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.Sorder;
import cn.hhit.eshop.model.SorderBean;

//购物项
public interface SorderService extends BaseService<Sorder> {
	
	//添加购物项
	public Forder addSorder(Forder forder, Product product);
	//商品封装成购物项
	public Sorder productToSorder(Product product);
	
	
	//前端和服务器端交互，需要将forder  和 forder 封装成bean
	SorderBean productToSorderBean(Product product) throws UnsupportedEncodingException;
	
	ForderBean addSorderBean(ForderBean forder, Product product);
	
	//查询销售统计数据
	List<Object> querySale(int number);
	
}
