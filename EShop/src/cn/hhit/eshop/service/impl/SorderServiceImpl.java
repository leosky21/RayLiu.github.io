package cn.hhit.eshop.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.Sorder;
import cn.hhit.eshop.model.SorderBean;
import cn.hhit.eshop.service.SorderService;
import cn.hhit.eshop.utils.ExByteUtil;

@Service("sorderService")
@Lazy(true)
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

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
	public ForderBean addSorderBean(ForderBean forder, Product product) {

		boolean bol = false;

		SorderBean sorder;
		try {
			sorder = productToSorderBean(product);

			for (SorderBean old : forder.getSorders()) {
				if (old.getProduct().getId().equals(sorder.getProduct().getId())) {
					old.setSnumber(old.getSnumber() + sorder.getSnumber());
					bol = true;
					break;
				}
			}
			if (!bol) {
				/**
				 * 将购物项加入订单之前，现设置其购物车建议购物项与购物车的关联， 但是此时forder.id为null， 但是会先入库
				 * forder ，生成forder.id ,这样sorder.forder.id就有主键了
				 */
				sorder.setForder(forder);
				forder.getSorders().add(sorder);
			}
		} catch (UnsupportedEncodingException e) {
			// 做异常处理 ：  返回连接 生成订单失败
			e.printStackTrace();
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

	@Override
	public SorderBean productToSorderBean(Product product) throws UnsupportedEncodingException {
		SorderBean sor = new SorderBean();

		sor.setProduct(ExByteUtil.ProductFormBean(product));
		sor.setSnumber(1);
		sor.setSprice(product.getPrice());
		sor.setSname(product.getName());

		return sor;
	}

	/**
	 * 查询销售统计数据
	 */
	@Override
	public List<Object> querySale(int number) {
		 
		return sorderDao.querySale(number);
	}

}
