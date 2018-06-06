package cn.ray.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static Properties config = new Properties();
	
	static{
		try{
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(config.getProperty("driver"));
		}catch (Exception e) {
			 /*
             * db.properties文件无法读取，那么整个应用程序无法连接数据库，
             * 驱动都加载不了，那么整个应用程序都无法工作，
             * 所以都应该抛一个错误(ExceptionInInitializerError)
             */
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				config.getProperty("url")
					, config.getProperty("username"), config.getProperty("password"));
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){
		if (rs!=null) {
            try {
                rs.close(); // 假设throw异常
            } catch (Exception e) {
                e.printStackTrace(); // 只需在后台记录异常
            }
            rs = null; // 假设rs对象没有释放，将其置为null，该对象就变成垃圾，由Java垃圾回收器回收
        }
        if (st!=null) {
            try {
                st.close(); // 假设throw异常
            } catch (Exception e) {
                e.printStackTrace(); // 只需在后台记录异常
            }
            st = null;
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(); // 只需在后台记录异常
            }
        }
	}
}
