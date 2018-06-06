package cn.hhit.eshop.dao;

import java.util.List;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.Sorder;

//购物项
public interface SorderDao extends BaseDao<Sorder> {
	
	//添加购物项,返回购物车
	public Forder addSorder(Forder forder, Product product);
	//商品封装成购物项
	public Sorder productToSorder(Product product);
	public List<Object> querySale(int number);
	
}
