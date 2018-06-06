package cn.ray.clob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.ray.utils.JdbcUtils;

public class TestClob {
/**
 * TODO clob 
		clob用于存储大文本。


	在实际开发中，有时是需要用程序把大文本或二进制数据直接保存到数据库中进行储存的。 
	对MySQL而言只有blob，而没有clob，MySQL存储大文本采用的是Text，Text和blob分别又分为：

		TINYTEXT、TEXT、MEDIUMTEXT和LONGTEXT
		TINYBLOB、BLOB、MEDIUMBLOB和LONGBLOB
 */
	/**
	 * 
	 * 写入:
	 * 
	   PreparedStatement.setCharacterStream(index, reader, length);
	 * //注意length长度须设置，并且设置为int型
	 * 
	 * 读取:
	    reader = resultSet.getCharacterStream(i);
		reader = resultSet.getClob(i).getCharacterStream();
		string s = resultSet.getString(i)
	 */
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	@Test
	public void insert() throws SQLException, FileNotFoundException {
		conn = JdbcUtils.getConnection();
		String sql = "insert into testclob(id,resume) values(?,?)";
		
		pst = conn.prepareStatement(sql);
		pst.setString(1, "1");
		
		File file = new File("src/1.txt");
		FileReader r = new FileReader(file);
		pst.setCharacterStream(2, r, file.length());
		int num = pst.executeUpdate();
        if (num > 0) {
            System.out.println("插入成功！！！");
        }
        
        JdbcUtils.release(conn, pst, rs);
        /**
1.建表的时候添加如下限制：ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
2.在my.cnf上修改如下：
------------------my.cnf------------------------------------------------------
# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/5.6/en/server-configuration-defaults.html
[client]
default-character-set=utf8mb4
[mysql]
default-character-set = utf8mb4
# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
 
# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin
# These are commonly set, remove the # and set as required.
# basedir = .....
# datadir = .....
# port = .....
# server_id = .....
# socket = .....
 
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M
 
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
log-error=/var/log/mysqld.log
long_query_time=3
 
[mysqld]
character-set-client-handshake = FALSE
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
init_connect='SET NAMES utf8mb4'
 
#log-slow-queries= /usr/local/mysql/log/slowquery.log
 ------------------------------------------------------------------------
重启mysql服务，service mysql stop；  service mysql start；问题解决。
造成这个问题的原因（网上找的）：
mysql中规定utf8字符的最大字节数是3，但是某些unicode字符转成utf8编码之后有4个字节，导致出错。
         */
	}
	@Test
    public void read() throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id,resume from testclob where id='1'";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                // String resume = rs.getString("resume");
                Reader reader = rs.getCharacterStream("resume");
                FileWriter writer = new FileWriter("1.txt");
                try {
                    int len = 0;
                    char[] buffer = new char[1024];
                    while ((len = reader.read(buffer)) > 0) { // 每次读取1024个字符
                        writer.write(buffer, 0, len);
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                } 

            }

        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

	
}
