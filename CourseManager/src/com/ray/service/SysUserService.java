package com.ray.service;

import com.ray.enitity.SysUser;

public interface SysUserService {
	public boolean findUserById(String userId,String pass);
	public boolean findUserByName(String userName,String pass);
	public void updateUser(SysUser user);
	public boolean saveUser(SysUser user);
	public void delete(SysUser user);
	public void alterUserName(SysUser user);
}
