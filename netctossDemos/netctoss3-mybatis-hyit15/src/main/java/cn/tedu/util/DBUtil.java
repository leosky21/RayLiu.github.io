package cn.tedu.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 数据库管理工具
 * 引入连接池来管理连接
 * 使用连接池，代替DriverManager管理连接
 * 
 * DBUtil工具，是DBTool工具的升级版
 */
public class DBUtil {
	
	//定义一个静态变量：连接池
	private static BasicDataSource dataSource;
	
	static {
		//1. 从配置文件中读取参数
		Properties prop = new Properties();
		try {
			prop.load(DBUtil.class.getClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取配置文件失败",e);
		}
		
		//2. 创建连接池
		dataSource = new BasicDataSource();
		
		//3. 将连接池的参数设置进去
		dataSource.setDriverClassName(prop.getProperty("driver"));
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("user"));
		dataSource.setPassword(prop.getProperty("password"));
		
		int initSize = Integer.parseInt(prop.getProperty("initSize"));
		dataSource.setInitialSize( initSize );
		
		int maxSize = Integer.parseInt(prop.getProperty("maxSize"));
		dataSource.setMaxActive(maxSize);
		
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * 由连接池创建的连接，close()方法被重新定义为归还连接的作用，
	 * 在归还时，将连接中的数据全部清空，并将该连接状态重置为空闲态
	 */
	public static void close( Connection conn ){
		if( conn!=null ){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("归还连接失败", e);
			}
		}
	}
}




