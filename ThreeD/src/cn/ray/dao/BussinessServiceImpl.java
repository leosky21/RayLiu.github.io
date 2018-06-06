package cn.ray.dao;

import java.util.List;

import cn.ray.beans.User;
import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.beans.QueryResult;

public class BussinessServiceImpl implements BussinessService {

	private UsersDao dao = new UsersDaoImpl();

	@Override
	public void addUser(User c) {
		dao.add(c);
	}

	@Override
	public void updateUser(User c) {
		dao.update(c);
	}

	@Override
	public void deleteUser(int id) {
		dao.delete(id);
	}

	@Override
	public User findUser(int id) {
		return dao.find(id);
	}

	@Override
	public List<User> getAllUser() {
		return dao.getAll();
	}

	public PageBean pageQuery(QueryInfo queryInfo) {
		System.out.println(queryInfo.getStartindex()+"::"+queryInfo.getPagesize());
		// 调用dao获取到页面数据
		QueryResult qr = dao.pageQuery(queryInfo.getStartindex(), queryInfo.getPagesize());
		
		// 根据dao的查询结果，生成页面显示需要的PageBean
		PageBean bean = new PageBean();
		bean.setCurrentpage(queryInfo.getCurrentpage());
		bean.setList(qr.getList());
		bean.setPagesize(queryInfo.getPagesize());
		bean.setTotalrecord(qr.getTotalrecord());

		return bean;
	}
}
