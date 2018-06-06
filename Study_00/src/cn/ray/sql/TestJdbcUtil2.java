package cn.ray.sql;

import java.sql.SQLException;
import java.util.List;

import cn.ray.session.DemoLogin02.User;



public class TestJdbcUtil2 {
	
	// 只是打样,看就好
	public void add(User a) throws SQLException {
        String sql = "insert into user(username,password) values(?,?)";
        Object[] params = {a.getUsername(), a.getPassword()};
        JdbcUtil2.update(sql, params);
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from user where username=?";
        Object[] params = {id};
        JdbcUtil2.update(sql, params);
    }

    public void update(User a) throws SQLException {
        String sql = "update user set username=?,password=?";
        Object[] params = {a.getUsername(), a.getPassword()};
        JdbcUtil2.update(sql, params);
    }

    public User find(String id) throws SQLException {
        String sql = "select * from user where username=?";
        Object[] params = {id};
        return (User) JdbcUtil2.query(sql, params, new BeanHandler(User.class));
    }

    public List getAll() throws SQLException {
        String sql = "select * from user";
        Object[] params = { };
        return (List) JdbcUtil2.query(sql, params, new BeanListHandler(User.class));
    }
}
