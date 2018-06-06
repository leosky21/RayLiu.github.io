package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.AdminDao;
import cn.tedu.entity.Admin;

/**
 * 业务处理类：具体业务的处理过程和代码
 */
@Service
public class LoginServiceImpl 
				implements LoginService {

	@Autowired
	private AdminDao adminDao;
	
	public Admin validate(
			String adminCode, String password) 
			throws AdminCodeException, PasswordException {
			
		//判断传入参数是否为空
		if(adminCode==null 
			|| adminCode.equals("")) {
			throw new AdminCodeException(
							"账号不能为空");
		}
		if(password==null
			|| password.equals("")) {
			throw new PasswordException(
							"密码不能为空");
		}
		
		//根据传入的账号，查询管理员
		Admin admin = 
			adminDao.findByCode(adminCode);
		//判断查询结果
		if(admin==null) {
			//账号不存在
			throw new AdminCodeException("账号不存在");
		} else {
			//继续判断密码是否正确
			if( password.equals(admin.getPassword())) {
				//验证通过，返回admin
				return admin;
			} else {
				//验证失败，返回带有错误信息的异常
				throw new PasswordException("密码错误");
			}
		}
	}

}
