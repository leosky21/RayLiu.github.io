package cn.hhit.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.Forder;
import cn.hhit.eshop.model.ForderBean;
import cn.hhit.eshop.model.Sorder;
import cn.hhit.eshop.model.SorderBean;
import cn.hhit.eshop.service.ForderService;

@Service("forderService")
@Lazy(true)
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {
	
	
	@Override
	public double cluTotalBean(ForderBean forder) {
		double total = 0.0;
		for (SorderBean sorder : forder.getSorders()) {
			total += sorder.getSnumber() * sorder.getSprice();
		}
		return total;
	}
	
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
		forderDao.saveForderBean(forder);
	}
}
