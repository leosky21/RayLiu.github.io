package com.ray.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ray.enitity.ClassInfo;
import com.ray.utils.DBOperator;

public class ClassDao {
	// 如果建立矩阵、要不要处理binary类型。
	
	
	DBOperator dbo = new DBOperator();

	public  boolean saveClass(ClassInfo classInfo) {
		String sql = "insert into class values (?,?,?,?)";
		boolean bol = dbo.executeClassInsert(classInfo.getcNum(),sql,
				classInfo.getClassName(),classInfo.getClassRoom(),classInfo.getcTeacher());
		return bol;
	}
	
	public boolean updateClass(ClassInfo classInfo) {
//		String sql = "update  class where CName = '"+classInfo.getClassName()+"' set"+; 
		//新建一个存储过程，一旦触发本方法，则先删除该条记录，同时新增修改信息	
		return false;
		}
	
	public String findClassByName(String className) throws SQLException {
		
		String sql = "select * from class where CName = '"+className+"'"; 
		ResultSet rs = dbo.query(sql);
		while(rs.next())
			return rs.getString("CName"); 
		return null;
	}

	public List<ClassInfo> findAllClass() throws SQLException {
		String sql = "select * from class";
		ResultSet rs= dbo.query(sql);
		List<ClassInfo> classes = new ArrayList<ClassInfo>();
		
		while(rs.next()){
			ClassInfo classInfo = new ClassInfo();
			classInfo.setClassName(rs.getString("CName"));;
			classInfo.setClassRoom(rs.getString("ClassRoom"));
			classInfo.setcTeacher(rs.getString("CTeacher"));
			classInfo.setcNum(rs.getInt("CNum"));
			classes.add(classInfo);
		}
		return classes;
		
	}
//	
//	public static void main(String[] args) throws SQLException {
//		ClassDao d= new ClassDao();
//		List<ClassInfo> list= new ArrayList<ClassInfo>(); 
//				list = d.findAllClass();
//				for(int i=0;i<list.size();i++){
//					System.out.println(list.get(i).getClassName());
//				}
//	}
}
