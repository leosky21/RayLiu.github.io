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
 * @author liujun
 *
 */
public class JdbcUtils {
	private static Properties p = new Properties();
	
	static{
		try {
			p.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(p.getProperty("driver"));
			System.out.println("JdbcUtils -----"+p.getProperty("url") );
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
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null; 
        }
        if (st!=null) {
            try {
                st.close(); 
            } catch (Exception e) {
                e.printStackTrace(); 
            }
            st = null;
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
	}
}
