package cn.ray.utils;

import java.sql.SQLException;

import org.junit.Test;

import cn.ray.beans.QueryResult;
import cn.ray.dao.StudentDao;

public class TestA {
	
	@Test
	public void test01(){
		try {
			JdbcUtils.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test02(){
		StudentDao s = new StudentDao();
		QueryResult q = s.pageQuery(1, 5);
		System.out.println(q.getTotalrecord());
	}
}
