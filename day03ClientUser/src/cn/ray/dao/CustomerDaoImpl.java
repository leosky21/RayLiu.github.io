package cn.ray.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.ray.beans.Customer;
import cn.ray.beans.QueryResult;
import cn.ray.exception.DaoException;
import cn.ray.utils.JdbcUtils;


public class CustomerDaoImpl implements CustomerDao {
	
	
	
	@Override
	public void add(Customer c) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
		conn = JdbcUtils.getConnection();
		String  sql = "insert into customer(id, name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
		st = conn.prepareStatement(sql);//
		String str= "abc";
		
		st.setString(1, new String(c.getId().getBytes(),"UTF-8"));
		st.setString(2, new String(c.getName().getBytes(),"UTF-8"));
        st.setString(3, new String(c.getGender().getBytes(),"UTF-8"));
        st.setDate(4, new Date(c.getBirthday().getTime()));
        st.setString(5, c.getCellPhone());
        st.setString(6, c.getEmail());
        st.setString(7, c.getPreference());
        st.setString(8, c.getType());
        st.setString(9, c.getDescription());
		
        st.executeUpdate();
		}catch (Exception e) {
			throw new DaoException(e);
		}finally {
            JdbcUtils.release(conn, st, rs);
        }
	}
	
	@Override
	public void update(Customer c) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
            st = conn.prepareStatement(sql);

            st.setString(1, c.getName());
            st.setString(2, c.getGender());
            st.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
            st.setString(4, c.getCellPhone());
            st.setString(5, c.getEmail());
            st.setString(6, c.getPreference());
            st.setString(7, c.getType());
            st.setString(8, c.getDescription());
            st.setString(9, c.getId());

            st.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
	
	@Override
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from customer where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            
            st.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
	
	@Override
	public Customer find(String id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from customer where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            
            rs = st.executeQuery();
            
            if (rs.next()) {
                Customer c = new Customer();
                c.setBirthday(rs.getDate("birthday"));
                c.setCellPhone(rs.getString("cellphone"));
                c.setDescription(rs.getString("description"));
                c.setEmail(rs.getString("email"));
                c.setGender(rs.getString("gender"));
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setPreference(rs.getString("preference"));
                c.setType(rs.getString("type"));
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
	public List<Customer> getAll() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from customer";
            st = conn.prepareStatement(sql);
            
            rs = st.executeQuery();
           
            List<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
                Customer c = new Customer();
                c.setBirthday(rs.getDate("birthday"));
                c.setCellPhone(rs.getString("cellphone"));
                c.setDescription(rs.getString("description"));
                c.setEmail(rs.getString("email"));
                c.setGender(rs.getString("gender"));
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setPreference(rs.getString("preference"));
                c.setType(rs.getString("type"));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

	@Override
	public QueryResult pageQuery(int startindex, int pagesize) {
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        QueryResult qr = new QueryResult();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from customer limit ?,?";
            st = conn.prepareStatement(sql);
            st.setInt(1, startindex);
            st.setInt(2, pagesize);
            rs = st.executeQuery();
            List<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
                Customer c = new Customer();
                c.setBirthday(rs.getDate("birthday"));
                c.setCellPhone(rs.getString("cellPhone"));
                c.setDescription(rs.getString("description"));
                c.setEmail(rs.getString("email"));
                c.setGender(rs.getString("gender"));
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setPreference(rs.getString("preference"));
                c.setType(rs.getString("type"));
                list.add(c);
            }
            qr.setList(list);
            // 还要进行一次查询，查询出总记录数
            sql = "select count(*) from customer";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                qr.setTotalrecord(rs.getInt(1));
            }
            return qr;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
	}
}
