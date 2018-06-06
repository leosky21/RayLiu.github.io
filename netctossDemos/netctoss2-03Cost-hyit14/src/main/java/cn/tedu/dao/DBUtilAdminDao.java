package cn.tedu.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.tedu.entity.Admin;
import cn.tedu.util.DBUtil;

/**
 * 管理员admin_info表 进行的dao操作
 */
public class DBUtilAdminDao implements Serializable{

	public Admin findByCode( String code ) {
		String sql = "select * from admin_info"
				+ " where admin_code=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
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
			DBUtil.close(conn);
		}
		return null;
	}
}





