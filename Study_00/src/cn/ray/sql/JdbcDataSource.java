package cn.ray.sql;

import java.io.PrintWriter;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.mysql.jdbc.Blob;

import com.mysql.jdbc.Clob;
import com.mysql.jdbc.DatabaseMetaData;

/**
 * 编写连接池需实现java.sql.DataSource接口。DataSource接口中定义了两个重载的getConnection方法：
 * 
 * Connection getConnection() Connection getConnection(String username, String
 * password)
 * 
 * @author ray
 *
 */
public class JdbcDataSource implements DataSource {
	/**
	 * 步骤: 1. 在DataSource构造函数中批量创建数据库的连接,并把创建的连接加入LinkedList # 之所以用LinkedList
	 * 是为了提高增删性能 2. 实现getConnection方法.每次调用则取一个Connection返回给用户 3.
	 * 调用用户调用close方法则返回给LinkedList
	 */

	private static LinkedList<Connection> list = new LinkedList<Connection>();

	@Override
	public Connection getConnection() {

		if (list.size() <= 0) {
			throw new RuntimeException("database is  busy ,wait...");
		}
		/*
		 * 千万不能调用list的get()方法取连接。 调用list的get()方法取连接，返回的只是这个连接的引用，但连接还在集合里面，
		 * 这就不行，我们说的取一个连接是从集合取了一个连接之后，再将其删掉。
		 */
		// Connection conn = list.get(1);
		Connection conn = list.removeFirst();
		// 如果这样的 ,使用者直接将其close,需每次用户调用release或者close的时候,返回给list

		/**
		 * 第一种方法 : 包装设计模式
		 * 一调用DBConnectionPool的getConnection()方法，拿到的是MyConnection，
		 * 然后调用MyConnection的prepareStatement(String
		 * sql)、commit()、createStatement()等方法，
		 * 但是只要一调用close()方法，即将包装的connection还到数据库连接池里面去了。 第二种方法: 动态代理
		 */
		MyConnection my = new MyConnection(conn);
		
		return my;
		
		/**
		 * 	第二种方法: 动态代理:
		 */
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	class MyConnection implements Connection {
		private Connection conn;

		public MyConnection(Connection conn) {
			this.conn = conn;
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Statement createStatement() throws SQLException {
			return this.conn.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return this.conn.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return this.conn.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void commit() throws SQLException {
			this.conn.commit();
		}

		@Override
		public void rollback() throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void close() throws SQLException {
			list.add(this.conn);
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clearWarnings() throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub

		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}