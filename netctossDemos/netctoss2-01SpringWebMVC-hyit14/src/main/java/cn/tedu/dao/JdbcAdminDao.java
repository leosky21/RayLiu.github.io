package cn.tedu.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.tedu.entity.Admin;
import cn.tedu.util.DBUtil;

/**
 * 在dao里面对数据库进行增删改查
 */
//@Component
@Repository
public class JdbcAdminDao 
		implements AdminDao, Serializable{

	@Autowired
	private DataSource dataSource;

	public Admin findByCode(String code) {
		String sql = "select * from admin_info"
				+ " where admin_code=?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			pst.setString(1, code);
			
			ResultSet rs = pst.executeQuery();
			if( rs.next() ) {
				Admin a = new Admin();
				a.setAdminId(rs.getInt("admin_id"));
				a.setAdminCode(rs.getString("admin_code"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("name"));
				a.setTelephone(rs.getString("telephone"));
				a.setEmail(rs.getString("email"));
				a.setEnrolldate(rs.getTimestamp("enrolldate"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"查询管理员信息失败",e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(
						"关闭连接失败", e);
			}
		}
		return null;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
