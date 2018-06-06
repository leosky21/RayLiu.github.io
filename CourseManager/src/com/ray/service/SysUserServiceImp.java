package com.ray.service;



import com.ray.dao.LgDao;
import com.ray.enitity.SysUser;

public class SysUserServiceImp implements SysUserService {
	LgDao ld = new LgDao();

	@Override
	public boolean findUserById(String userId,String pass) {
		//TODO：b、由LoginAction传来第一个action，为了提供服务，向dao层获取数据
		return ld.findSysUserById(userId,pass);
	}

	@Override
	public boolean findUserByName(String name,String pass) {
		return ld.findSysUserByName(name,pass);
	}

	@Override
	public void updateUser(SysUser user) {
		
		
	}

	@Override
	public boolean saveUser(SysUser user) {
		return ld.saveUser(user);
		
	}

	@Override
	public void delete(SysUser user) {

		
	}

	@Override
	public void alterUserName(SysUser user) {
		
		ld.alterUserName(user);
	}


}
