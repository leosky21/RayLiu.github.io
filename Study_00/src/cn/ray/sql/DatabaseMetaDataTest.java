package cn.ray.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

public class DatabaseMetaDataTest {
	
	
	@Test
	public void testDatabaseMetaData() throws SQLException {
		/**
		 	getURL()：返回一个String类对象，代表数据库的URL。
			getUserName()：返回连接当前数据库管理系统的用户名。
			getDatabaseProductName()：返回数据库的产品名称。
			getDatabaseProductVersion()：返回数据库的版本号。
			getDriverName()：返回驱动程序的名称。
			getDriverVersion()：返回驱动程序的版本号。
			isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作。
		 */
		Connection conn = JdbcUtils.getConnection();
		DatabaseMetaData meta = conn.getMetaData();
		
		System.out.println(meta.getDriverName());
		System.out.println(meta.getURL());
	}
	
	@Test
	public void testParameterMetaData() throws SQLException {
		/**
		 * 常用:
		  	getParameterCount()： 获得指定参数的个数。
			getParameterType(int param)：获得指定参数的sql类型。MySQL数据库驱动不支持。
		 */
		Connection conn = JdbcUtils.getConnection();
		//DatabaseMetaData meta = conn.getMetaData();
		
		String sql = "insert into student(id,name) values(?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		ParameterMetaData meta = pst.getParameterMetaData();
		
		System.out.print(meta.getParameterCount());
		// MySQL驱动不支持获取参数类型，即第一个参数需要什么类型的数据
		System.out.println(meta.getParameterType(1));
		
	}
	
	@Test
	public void testResultSetMetaData() throws SQLException {
		/**
		 * 常用	:
		 	getColumnCount()：返回resultset对象的列数。
			getColumnName(int column)：获得指定列的名称。
			getColumnTypeName(int column)：获得指定列的类型
		 */
		Connection conn = JdbcUtils.getConnection();
		String sql = "select * from student";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs= pst.executeQuery();
		
		ResultSetMetaData meta = rs.getMetaData();
		System.out.println("meta.getColumnCount()::"+meta.getColumnCount());
		System.out.println("meta.getColumnName(1)::"+meta.getColumnName(1));
		
	}
	

}
