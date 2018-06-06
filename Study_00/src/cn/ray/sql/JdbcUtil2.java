package cn.ray.sql;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;

public class JdbcUtil2 {

	private static DataSource ds = null;

	// 静态代码块只执行一次，因为静态代码块在类加载时执行，类永远只加载一次
	static {
		// 初始化连接池

		try {
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);

			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection(); // 不会将真正的MySQL驱动返回的Connection返回给你
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close(); // 假设throw异常
			} catch (Exception e) {
				e.printStackTrace(); // 只需在后台记录异常
			}
			rs = null; // 假设rs对象没有释放，将其置为null，该对象就变成垃圾，由Java垃圾回收器回收
		}
		if (st != null) {
			try {
				st.close(); // 假设throw异常
			} catch (Exception e) {
				e.printStackTrace(); // 只需在后台记录异常
			}
			st = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace(); // 只需在后台记录异常
			}
		}

	}

	// 抽取增删改的公共代码
	/*
	 * String sql = "insert into account(id,name,money) values(?,?,?)";
	 * Object[]{1, "aaa", 10000}
	 */
	public static void update(String sql, Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			st = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
			}

			st.executeUpdate();
		} finally {
			release(conn, st, rs);
		}
	}

	// 抽取查询的公共代码，优化查询，替换掉所有的查询
	public static Object query(String sql, Object[] params, ResultSetHandler handler) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
			}

			/*
			 * 框架的设计者是不知道要执行的sql语句的， 执行该sql语句，拿到的结果集，如何对结果集进行处理框架的设计者也是不知道的
			 * 那该怎么办呢？框架的设计者不知道没关系，但使用该框架的人是知道怎么对结果集进行处理的， 那就让他去做这种事情。
			 * 代码该这样写：我对外暴露一个接口，使用该框架的人去实现该接口做这种事情，我针对接口进行调用。这是一种设计模式——策略模式
			 */
			rs = st.executeQuery();

			return handler.handler(rs);

		} finally {
			release(conn, st, rs);
		}

	}

}

interface ResultSetHandler {

	public Object handler(ResultSet rs);

}

// 框架设计者在编写这个处理器的时候，并不知道把结果集处理到哪个对象里面去，
// 框架的设计者不知道没关系，但使用该框架的人总该知道，到时候在使用这个结果集处理器时传给框架设计者
class BeanHandler implements ResultSetHandler {

	private Class clazz;

	public BeanHandler(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object handler(ResultSet rs) {

		try {
			if (!rs.next()) {
				return null;
			}
			// 创建封装结果集的bean
			Object bean = clazz.newInstance();

			// 得到结果集的元数据，以获取结果集的信息
			ResultSetMetaData meta = rs.getMetaData();
			int count = meta.getColumnCount();
			for (int i = 0; i < count; i++) {
				String name = meta.getColumnName(i + 1); // 获取到结果集每列的列名 id
				Object value = rs.getObject(name); // 1

				// 反射出bean上与列名相应的属性
				Field f = bean.getClass().getDeclaredField(name);
				f.setAccessible(true);
				f.set(bean, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

class BeanListHandler implements ResultSetHandler {

	private Class clazz;

	public BeanListHandler(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object handler(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				Object bean = clazz.newInstance();

				ResultSetMetaData meta = rs.getMetaData();
				int count = meta.getColumnCount();
				for (int i = 0; i < count; i++) {
					String name = meta.getColumnName(i + 1);
					Object value = rs.getObject(name);

					Field f = bean.getClass().getDeclaredField(name);
					f.setAccessible(true);
					f.set(bean, value);
				}
				list.add(bean);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
