package com.ray.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ray.enitity.TeacherInfo;
import com.ray.utils.DBOperator;

public class TeacherDao {
	DBOperator dbo = new DBOperator();

	public  boolean saveTeacher(TeacherInfo teacherInfo) {
		String sql = "insert into teacher(TID,TName,courseName,TNum,Tmana)  values (?,?,?,?,?)";
		/**与class 都是int 与String 并存*/
		boolean bol = dbo.executeClassInsert(teacherInfo.gettMana(),sql,teacherInfo.gettId(),teacherInfo.gettName(),
				teacherInfo.gettCourseName(),teacherInfo.gettTel());
		return bol;
	}
	
	public boolean updateTeacher(TeacherInfo teacherInfo) {
//		String sql = "update  teacher where TName = '"+teacherInfo.gettName()+"' set "+; 
		//新建一个存储过程，一旦触发本方法，则先删除该条记录，同时新增修改信息	
		return false;
		}
	
	public String findTeacherByName(String teacherName) throws SQLException {
		
		String sql = "select * from teacher where TName = '"+teacherName+"'"; 
		ResultSet rs = dbo.query(sql);
		while(rs.next())
			return rs.getString("CName");
		return null;
	}

	public List<TeacherInfo> findAllTeacher() throws SQLException {
		String sql = "select * from teacher";
		ResultSet rs= dbo.query(sql);
		List<TeacherInfo> teacheres = new ArrayList<TeacherInfo>();
		
		while(rs.next()){
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.settId(rs.getString("TID"));
			teacherInfo.settName(rs.getString("TName"));
			teacherInfo.settTel(rs.getString("TNum"));
			teacherInfo.settCourseName(rs.getString("courseName"));
			teacherInfo.settMana(rs.getInt("TMana"));
			teacheres.add(teacherInfo);
		}
		return teacheres;
	}
	
//
//	public static void main(String[] args) throws SQLException {
//		TeacherDao d= new TeacherDao();
//		List<TeacherInfo> list= new ArrayList<TeacherInfo>(); 
//				list = d.findAllTeacher();
//				for(int i=0;i<list.size();i++){
//					System.out.println(list.get(i).gettId());
//				}
//	}
}
