package cn.hhit.eshop.dao;

import java.util.List;

import cn.hhit.eshop.model.Category;

public interface CategoryDao extends BaseDao<Category> {

	/*
	 * 只要添加CategoryService本身需要的新的方法即可，公共方法已经在BaseService中了
	 */
	// 查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type); // 使用类别的名称查询
	// 查询类别信息，级联管理员

	public List<Category> queryJoinAccount(String type, int page, int size); // 并实现分页

	public Long getCount(String type);

	// 根据ids删除多条记录
	public void deleteByIds(String ids);
	
	//根据boelen值查询热点或非热点类别  
    public List<Category> queryByHot(boolean hot);  
}
