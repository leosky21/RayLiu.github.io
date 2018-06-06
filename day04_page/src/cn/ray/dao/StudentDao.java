package cn.ray.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.ray.beans.QueryResult;
import cn.ray.beans.Student;
import cn.ray.utils.JdbcUtils;

public class StudentDao {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public QueryResult pageQuery(int startindex, int pagesize){
		QueryResult qr = new QueryResult();
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select * from student limit ?,?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, startindex);
			pst.setInt(2, pagesize);
			
			rs = pst.executeQuery();
			List<Student> list = new ArrayList<Student>();
			while(rs.next()){
				list.add(new Student(rs.getString("id"), rs.getString("name")));		
			}
			qr.setList(list);
			
			String count = "select count(*) from student";
			rs = conn.prepareStatement(count).executeQuery();
			if(rs.next()){
				qr.setTotalrecord(rs.getInt(1));
			}
			return qr;
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtils.release(conn, pst, rs);
		}
	}
}
