package com.jf.service;

import java.sql.SQLException;

import com.jf.model.Admin;

public interface AdminServiceI {

	public Admin getAdminByNameAndPwd(String username, String pwd) throws SQLException;

	public int updateAdminInfo(Integer id, String name, String phone);

	public int UpdatePwd(String newPwd, Integer id);

}
