package com.jf.dao;

import java.sql.SQLException;

import com.jf.model.Admin;
import com.jf.model.Authorization;

public interface AdminDaoI {

	public Admin getAdmin(String username, String pwd) throws SQLException;

	public Authorization getAuthorization(Integer aid) throws SQLException;

	public int updateAdminInfo(Integer id, String name, String phone) throws SQLException;

	public int UpdatePwd(String newPwd, Integer id) throws SQLException;

}
