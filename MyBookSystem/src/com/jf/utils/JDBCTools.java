package com.jf.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * JDBC 的工具类
 * <p>
 * 其中包含: 获取数据库连接, 关闭数据库资源等方法.
 */
public class JDBCTools {
    private static DataSource ds = new ComboPooledDataSource();
    /**
     * 它为null表示没有事务
     * 它不为null表示有事务
     * 当开启事务时，需要给它赋值
     * 当结束事务时，需要给它赋值为null
     * 并且在开启事务时，让dao的多个方法共享这个Connection
     */
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * dao使用本方法来获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        /*
         * 如果有事务，返回当前事务的con
         * 如果没有事务，通过连接池返回新的con
         */
        Connection conn = tl.get();//获取当前线程的事务连接
//        if(con != null) return con;
//        return ds.getConnection();

        if (conn == null) {
            conn = ds.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        Connection conn = tl.get();//获取当前线程的事务连接
        if (conn != null) {
            conn.commit();//提交事务
        }
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        Connection conn = tl.get();//获取当前线程的事务连接
        if (conn != null) {
            conn.rollback();
        }
    }

    public static   void close(){
        Connection conn = tl.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                tl.remove();
                conn = null;
            }
        }

    }

}  