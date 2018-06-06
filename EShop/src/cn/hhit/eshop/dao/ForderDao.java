package cn.hhit.eshop.dao;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;

public interface ForderDao extends BaseDao<Forder> {
	
	//计算购物车内类购物项总价格
	public double cluTotal(Forder forder);

	public void saveForderBean(ForderBean forder);
}
