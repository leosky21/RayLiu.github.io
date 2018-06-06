package cn.tedu.service;

import cn.tedu.entity.Admin;

public interface LoginService {

	Admin validate(
		String adminCode, String password)
				throws AdminCodeException, 
						PasswordException;

}
