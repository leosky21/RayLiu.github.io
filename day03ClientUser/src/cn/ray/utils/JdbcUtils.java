package cn.ray.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * TODO PreparedStatement下中文乱码的问题
 * 	con = 
 * 		DriverManager.getConnection(
 * 			"jdbc:mysql://localhost/onlinebookshop?characterEncoding=utf8", "root", "admin"); 
 * @author liujun
 *
 */
public class JdbcUtils {
	private static Properties p = new Properties();
	
	static{
		try {
			p.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(p.getProperty("driver"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(p.getProperty("url")+"?characterEncoding=utf8", p.getProperty("username"), p.getProperty("password"));
	}
	
	public static void release(Connection conn , Statement st, ResultSet rs){
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
