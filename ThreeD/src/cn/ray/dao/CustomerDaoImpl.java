package cn.ray.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.ray.beans.Customer;
import cn.ray.exception.DaoException;
import cn.ray.utils.JdbcUtils;


public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void add(Customer customer) {
       
    }
	 // 查找注册的用户是否在数据库中存在
    @Override
	public boolean find(String username) {
		
    	return false; 
    }
    @Override
	public Customer find(String username, String password) {
    	Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from customer where name=?";
            st = conn.prepareStatement(sql);
            st.setString(1, username);
           
            rs = st.executeQuery();
            
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setPassword(rs.getString("password"));
               System.out.println("CustomerDaoImpl:::"+c.getId()+"::"+c.getName()+"::"+c.getPassword());
                return c;
            }
            return null;
        } catch (Exception e) {
            throw new DaoException("-----findCustomer"+e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
