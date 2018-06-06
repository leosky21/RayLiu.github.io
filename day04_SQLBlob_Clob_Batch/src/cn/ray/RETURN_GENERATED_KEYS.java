package cn.ray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.ray.utils.JdbcUtils;

public class RETURN_GENERATED_KEYS {
	// 获取自动生成的主键
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into test(name) values('yelei')";
            /*
             * PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) 
             * autoGeneratedKeys - 指示是否应该返回自动生成的键的标志，它是 Statement.RETURN_GENERATED_KEYS或Statement.NO_GENERATED_KEYS之一 
             * 
             * st = conn.prepareStatement(sql)默认是不返回自动生成的主键
s            */
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement.RETURN_GENERATED_KEYS参数——是否得到sql语句生产的主键
            st.executeUpdate();

            rs = st.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            }

        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
}
