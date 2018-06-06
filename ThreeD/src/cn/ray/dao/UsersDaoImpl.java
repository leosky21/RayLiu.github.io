package cn.ray.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import cn.ray.beans.QueryResult;
import cn.ray.beans.User;
import cn.ray.exception.DaoException;
import cn.ray.utils.JdbcUtils;

public class UsersDaoImpl implements UsersDao {

	@Override
	public void add(User c) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into users(name,password,company,tel) values(?,?,?,?)";
			st = conn.prepareStatement(sql);//
			st.setString(1, new String(c.getUsername().getBytes(), "UTF-8"));
			st.setString(2, new String(c.getPassword().getBytes(), "UTF-8"));
			st.setString(3, new String(c.getCompany().getBytes(), "UTF-8"));
			st.setString(4, new String(c.getTel().getBytes(), "UTF-8"));
			st.execute();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	@Override
	public void update(User c) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update users set name=?,company=?,tel=?,password=? where id=?";
			st = conn.prepareStatement(sql);
			
			st.setString(1, c.getUsername());
			st.setString(2, c.getCompany());
			st.setString(3, c.getTel());
			st.setString(4, c.getPassword());
			st.setInt(5, c.getId());

			st.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	@Override
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from users where id=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, id);

			st.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	@Override
	public User find(int id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where id=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("id"));
				c.setUsername(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				c.setCompany(rs.getString("company"));
				c.setTel(rs.getString("tel"));
				return c;
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	@Override
	public List<User> getAll() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users";
			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			List<User> list = new ArrayList<User>();
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("id"));
				c.setUsername(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				c.setCompany(rs.getString("company"));
				c.setTel(rs.getString("tel"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public QueryResult pageQuery(int startindex, int pagesize) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		QueryResult qr = new QueryResult();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users limit ?,?";
			st = conn.prepareStatement(sql);
			st.setInt(1, startindex);
			st.setInt(2, pagesize);
			rs = st.executeQuery();
			List<User> list = new ArrayList<User>();
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("id"));
				c.setUsername(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				c.setCompany(rs.getString("company"));
				c.setTel(rs.getString("tel"));
				list.add(c);
			}
			qr.setList(list);
			// 还要进行一次查询，查询出总记录数
			sql = "select count(*) from users";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				qr.setTotalrecord(rs.getInt(1));
				System.out.println(qr.getTotalrecord());
			}
			return qr;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
}
