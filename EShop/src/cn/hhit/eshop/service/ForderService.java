package cn.hhit.eshop.service;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;

public interface ForderService extends BaseService<Forder> {
	
	//计算购物车内类购物项总价格
	public double cluTotal(Forder forder);

	double cluTotalBean(ForderBean forder);

	public void saveForderBean(ForderBean forder);
}
