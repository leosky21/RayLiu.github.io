package cn.ray.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.ray.utils.JdbcUtils;

/**
 * 对于MySQL中的BLOB类型，可调用如下方法设置：

	PreparedStatement.setBinaryStream(i, inputStream, length);
	
	对MySQL中的BLOB类型，可调用如下方法获取：
	
	InputStream in = resultSet.getBinaryStream(i);
	InputStream in = resultSet.getBlob(i).getBinaryStream();

 * @author ray
 *
 */
public class TestBlob {
	 @Test
	    public void insert() throws SQLException, FileNotFoundException {
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;

	        try {
	            conn = JdbcUtils.getConnection();
	            String sql = "insert into testblob(id,image) values(?,?)";
	            st = conn.prepareStatement(sql);
	            st.setString(1, "1");

	            File file = new File("src/1.png");
	            FileInputStream in = new FileInputStream(file);
	            st.setBinaryStream(2, in, file.length());
	            int num = st.executeUpdate();
	            if (num > 0) {
	                System.out.println("插入成功！！！");
	            }
	        } finally {
	            JdbcUtils.release(conn, st, rs);
	        }
	    }
	 
	 @Test
	    public void read() throws SQLException, IOException {
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;

	        try {
	            conn = JdbcUtils.getConnection();
	            String sql = "select id,image from testblob where id='1'";
	            rs = conn.prepareStatement(sql).executeQuery();
	            if (rs.next()) {
	                InputStream in = rs.getBinaryStream("image");
	                OutputStream out = new FileOutputStream("1.png");
	                try {
	                    int len = 0;
	                    byte[] buffer = new byte[1024];
	                    while ((len = in.read(buffer)) > 0) {
	                        out.write(buffer, 0, len);
	                    }
	                } finally {
	                    if (in != null)
	                        in.close();
	                    if (out != null)
	                        out.close();
	                }

	            }

	        } finally {
	            JdbcUtils.release(conn, st, rs);
	        }
	    }
}
