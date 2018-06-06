package cn.hhit.eshop.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.ForderDao;
import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.Sorder;
import cn.hhit.eshop.model.SorderBean;
import cn.hhit.eshop.utils.ExByteUtil;

@Repository("forderDao")
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {

	@Override
	public double cluTotal(Forder forder) {
		double total = 0.0;
		for (Sorder sorder : forder.getSorders()) {
			total += sorder.getSnumber() * sorder.getSprice();
		}
		return total;
	}

	@Override
	public void saveForderBean(ForderBean forder) {
		 Forder forder1 = new Forder();
		 Set<SorderBean> ssb =	forder.getSorders();
		 Set<Sorder> ss = new HashSet<Sorder>();
		 Iterator<SorderBean> it = ssb.iterator();
		 while(it.hasNext()){
			 SorderBean sorder = it.next();
			 
			 ss.add(new Sorder(
					 forder1,
					 new Product(sorder.getProduct().getId()),
					 sorder.getSname(),
					 sorder.getSprice(),
					 sorder.getSnumber()
					 	)
					 );
		 }
		 forder1.setUser(forder.getUser());
		 forder1.setFaddress(forder.getFaddress());
		 forder1.setFdate(forder.getFdate());
		 forder1.setFname(forder.getFname());
		 forder1.setFphone(forder.getFphone());
		 forder1.setFpost(forder.getFpost());
		 forder1.setFremark(forder.getFremark());
		 forder1.setSorders(ss);
		 forder1.setStatus(forder.getStatus());
		 forder1.setFtotal(forder.getFtotal());
		getSession().save(forder1);
	}

}
