package com.ray.dao;


//import javax.swing.JOptionPane;
import com.ray.enitity.SysUser;
import com.ray.utils.DBOperator;

public class LgDao {
	DBOperator dbo = new DBOperator();

	public boolean findSysUserById(String userId,String pass){	
		//针对数据库操作
		String sql = "select * from yhxxb where userID = '"+userId+"'and userPass = '"+pass+ "'"; 
		
		boolean bol = dbo.loginQuery(sql);
		return bol;
	}
	public boolean findSysUserByName(String userName,String pass){
		String sql = "select * from yhxxb where userName = '"+userName+"'and userPass = '"+pass+ "'"; 
		boolean bol = dbo.loginQuery(sql);
		return bol;
	}
	public boolean saveUser(SysUser user){
		String sql = "insert into yhxxb values (?,?,?,?,?)"; 
		user.setUserType("teacher");
		boolean bol = dbo.executeInsert(sql,user.getUserID(),user.getUserName(),user.getUserType(),user.getPass(),user.getTel());
		return bol;
	}
	public void alterUserName(SysUser user) {
		String sql = "update yhxxb set userPass='"+user.getPass()+"' where userID='"+user.getUserID()+"'";
		 dbo.executeDb(sql);
		
	}
}
