package cn.ray.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.ray.utils.JdbcUtils;

public class TestBatch {
	  /*
    create table testbatch
    (
        id varchar(40) primary key,
        name varchar(40)
    );

    */
	// 实现批处理的第一种方式
    @Test
    public void test1() throws SQLException {
/**
 * 采用Statement.addBatch(sql)方式实现批处理：

优点：可以向数据库发送多条不同的SQL语句。
缺点：
	SQL语句没有预编译。
	当向数据库发送多条语句相同，但仅参数不同的SQL语句时，需重复写上很多条SQL语句
 */
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql1 = "insert into testbatch(id,name) values('1','ana')"; // 1.可以发送不同的sql语句 2.发送的sql语句没有预编译
            String sql2 = "update testbatch set name='bmbm' where id='1'";

            st = conn.createStatement(); // Statement内部维护了一个List集合，addBatch()方法加入的sql语句都加入到该List集合内了。
            st.addBatch(sql1);
            st.addBatch(sql2);

            st.executeBatch(); // 返回int[]  [3,4]——即第一条sql语句执行影响数据库表几行，第二条sql语句执行影响数据库表几行，...
            st.clearBatch(); // 清除清除批处理命令
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
 // 实现批处理的第二种方式
    @Test
    public void test2() throws SQLException {
/**
 * 	优点：发送的是预编译后的SQL语句，执行效率高。
	缺点：只能应用在SQL语句相同，但参数不同的批处理中。因此此种形式的批处理经常用于在同一个表中批量插入数据，或批量更新表的数据。
 */
        long starttime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql1 = "insert into testbatch(id,name) values(?,?)"; // 1.适合做批量插入和批量更新   2.发送的sql语句都预编译过，性能会好一点。实际开发里，此种方式用到的多一点。
            st = conn.prepareStatement(sql1);

            /*
            st.setString(1, "1");
            st.setString(2, "aaabab");
            st.addBatch(); // 把PreparedStatement对象内部封装的sql语句加入到PreparedStatement对象内部的List集合中

            st.setString(1, "2");
            st.setString(2, "aabba");
            st.addBatch();  // 此时PreparedStatement对象内部的List集合中有两条sql语句
            */

            // 批处理一千万条sql语句，会出现OutOfMemoryError:Java heap space——内存溢出
            /*
            for (int i = 1; i <= 10000000; i++) {
                st.setString(1, i+"");
                st.setString(2, "aabba"+i);
                st.addBatch();
            }
            st.executeBatch();
            */
            for (int i = 200000; i <= 400006; i++) {
                st.setString(1, i+"");
                st.setString(2, "aabba"+i);
                st.addBatch();

                if (i%1000==0) {
                    st.executeBatch();
                    st.clearBatch();
                }
                st.executeBatch(); // 剩下不到1000条sql语句也需要执行
            }
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

        long endtime = System.currentTimeMillis();
        System.out.println("共花了："+(endtime-starttime)/1000+"秒"); // 向MySQL数据库批插入1000万条记录，大概需要3个多小时。而Oracle数据库测试大概需要380秒
    }
}
