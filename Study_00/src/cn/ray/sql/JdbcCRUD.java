package cn.ray.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.ray.session.DemoLogin02.User;

public class JdbcCRUD {
	
	/*使用executeUpdate(String sql)方法完成数据添加操作。*/
	public void insert() throws SQLException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        conn = JdbcUtils.getConnection();
	        st = conn.createStatement();
	        String sql = "insert into users(id,name,password,email,birthday) values(4,'liayun','123321','liayun@163.com','1992-10-06')";
	        int num = st.executeUpdate(sql);
	        if (num>0) {
	            System.out.println("插入成功！！！");
	        }
	    } finally {
	        JdbcUtils.release(conn, st, rs);
	    }
	}
	
	/*使用executeUpdate(String sql)方法完成数据修改操作。*/
	public void update() throws SQLException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        conn = JdbcUtils.getConnection();
	        st = conn.createStatement();
	        String sql = "update users set name='liyunling' where id='4'";
	        int num = st.executeUpdate(sql);
	        if (num>0) {
	            System.out.println("更新成功！！！");
	        }
	    } finally {
	        JdbcUtils.release(conn, st, rs);
	    }
	}
	
	/*使用executeUpdate(String sql)方法完成数据删除操作。*/
	public void delete() throws SQLException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        conn = JdbcUtils.getConnection();
	        st = conn.createStatement();
	        String sql = "delete from users where id=4";
	        int num = st.executeUpdate(sql);
	        if (num>0) {
	            System.out.println("删除成功！！！");
	        }
	    } finally {
	        JdbcUtils.release(conn, st, rs);
	    }
	}
	/*使用executeQuery(String sql)方法完成数据查询操作。*/

	/*- 根据id来查询用户信息。*/
	public void find() throws SQLException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        conn = JdbcUtils.getConnection();
	        st = conn.createStatement();
	        String sql = "select id,name,password,email,birthday from users where id=1";
	        rs = st.executeQuery(sql);
	        User user = null;
	        if(rs.next()) {
	            user = new User();
//	            user.setId(rs.getInt("id"));// === user.getId(rs.getInt(1));
	            user.setUsername(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
//	            user.setEmail(rs.getString("email"));
//	            user.setBirthday(rs.getDate("birthday"));
	        }
	        System.out.println(user);
	    } finally {
	        JdbcUtils.release(conn, st, rs);
	    }
	}
	/*	- 查询所有用户的信息*/
	public void getAll() throws SQLException {
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        conn = JdbcUtils.getConnection();
	        st = conn.createStatement();
	        String sql = "select id,name,password,email,birthday from users";
	        rs = st.executeQuery(sql);
	        List<User> list = new ArrayList<User>();
	        while(rs.next()) {
	            User user = new User();
//	            user.setId(rs.getInt("id"));// === user.getId(rs.getInt(1));
	            user.setUsername(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
//	            user.setEmail(rs.getString("email"));
//	            user.setBirthday(rs.getDate("birthday"));
	            list.add(user);
	        }
	        System.out.println(list); // 断点查看
	    } finally {
	        JdbcUtils.release(conn, st, rs);
	    }
	}
}
