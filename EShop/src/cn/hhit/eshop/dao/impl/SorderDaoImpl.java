package cn.hhit.eshop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.SorderDao;
import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.Sorder;

@Repository("sorderDao")
// @Lazy(true)
public class SorderDaoImpl extends BaseDaoImpl<Sorder> implements SorderDao {

	@Override
	public Forder addSorder(Forder forder, Product product) {
		boolean bol = false;

		Sorder sorder = productToSorder(product);

		for (Sorder old : forder.getSorders()) {
			if (old.getProduct().getId().equals(sorder.getProduct().getId())) {
				old.setSnumber(old.getSnumber() + sorder.getSnumber());
				bol = true;
				break;
			}
		}
		if (!bol) {
			/**
			 * 将购物项加入订单之前，现设置其购物车建议购物项与购物车的关联， 但是此时forder.id为null， 但是会先入库 forder
			 * ，生成forder.id ,这样sorder.forder.id就有主键了
			 */
			sorder.setForder(forder);
			forder.getSorders().add(sorder);
		}

		return forder;
	}

	@Override
	public Sorder productToSorder(Product product) {
		Sorder sor = new Sorder();

		sor.setProduct(product);
		sor.setSnumber(1);
		sor.setSprice(product.getPrice());
		sor.setSname(product.getName());

		return sor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querySale(int number) {
		String hql = "select s.sname, sum(s.snumber) from Sorder s join s.product group by s.product.id";
		return getSession().createQuery(hql) 
				.setFirstResult(0) 
				.setMaxResults(number) 
				.list();
	}

}
