package com.jf.service.impl;

import java.sql.SQLException;

import com.jf.dao.AdminDaoI;
import com.jf.dao.impl.AdminDaoImpl;
import com.jf.model.Admin;
import com.jf.model.Authorization;
import com.jf.service.AdminServiceI;
import com.jf.utils.JDBCTools;

public class AdminServiceImpl implements AdminServiceI {

	private AdminDaoI addao = new AdminDaoImpl();

	@Override
	public Admin getAdminByNameAndPwd(String username, String pwd)
			throws SQLException {
		// 需要在service层给管理员附加权限
		Admin admin = new Admin();
		admin = addao.getAdmin(username, pwd);
		// 开始查询权限
		// 权限表的id和admin表的id是一一对应关系
		// 获得了admin的id就意味着获得了权限表的id
		// 权限表的名称 Authorization
		// 在获取权限之前判断admin是否为空
		if (admin != null) {
			Integer aid = admin.getId();
			// 查询权限,其实就是根据主键aid查询
			Authorization auth = addao.getAuthorization(aid);
			// 把权限信息放到admin中
			admin.setAuthorization(auth);
			return admin;
		}
		return null;
	}

	@Override
	public int updateAdminInfo(Integer id, String name, String phone) {
		// 更新操作需要先开启事务
		try {
			JDBCTools.beginTransaction();
			int num = addao.updateAdminInfo(id, name, phone);
			// 需要提交事务
			JDBCTools.commitTransaction();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int UpdatePwd(String newPwd, Integer id) {
		// 更新操作需要先开启事务
		try {
			JDBCTools.beginTransaction();
			int num = addao.UpdatePwd(newPwd,id);
			// 需要提交事务
			JDBCTools.commitTransaction();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
