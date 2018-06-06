package cn.hhit.eshop.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.service.CategoryService;


@Service("categoryService")
@Lazy(true)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	public List<Category> queryJoinAccount(String type) {
		return categoryDao.queryJoinAccount(type);
	}

	/**
	 * 
	 * TODO 级联: 将fetch设置生FetchType.LAZY就不会发多条语句了， 但是设置成LAZY后，
	 * 我们就拿不到Account对象了，比较好的解决方法是我们自己写hql语句，使用join fetch。
	 */
	/**
	 * TODO 分页
	 */
	@Override
	public List<Category> queryJoinAccount(String type, int page, int size) {
		return categoryDao.queryJoinAccount(type, page, size);
	}

	@Override
	public Long getCount(String type) {
		return categoryDao.getCount(type);
	}

	@Override
	public void deleteByIds(String ids) {
		categoryDao.deleteByIds(ids);
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		return categoryDao.queryByHot(hot);
	}

}
