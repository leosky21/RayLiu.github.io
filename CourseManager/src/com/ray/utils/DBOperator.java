package com.ray.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBOperator {
	
	private String ClassName="com.mysql.jdbc.Driver";//mysql数据库驱动名称
	private String URl = "jdbc:mysql://127.0.0.1:3306/CourseSys"; //数据库访问路径
	private String User = "root";
	private String Pass ="Zx..00..";
	public static Connection conn = null;// MySQL数据库的连接对象
//    private PreparedStatement ps=null;//定义sql语句的执行对象
//    private ResultSet rs=null;//定义查询返回的结果集合
	public void Conncetion(){
		try{
				/**
				 * TODO：1、加载数据库驱动名称
				 * TODO：2、定义数据库访问连接
				 * TODO：3、创建通过数据库的连接操作===>  Statement st = conn.createStatement();rs = st.executeQuery(Sql);
				 */
				Class.forName(ClassName);//注册驱动、加载驱动程序
				conn = DriverManager.getConnection(URl, User, Pass);//定义连接
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "没有数据库驱动");
			System.exit(0);//退出
		}catch (Exception e1) {
			JOptionPane.showConfirmDialog(null, "未连接到数据库");
			e1.printStackTrace();
		}
	}
	
	
	  public ResultSet query(String sql){
		  Conncetion();
		  ResultSet rs= null;
	    	try {
	    		//TODO：d、连接数据库，执行SQl语句
	        	Statement stm=conn.createStatement();//
	        	rs= stm.executeQuery(sql);			
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	return rs;
	    }
	  /**
	   *
	   *@return bol 是否验证到
	   */
	  public boolean loginQuery(String sql){
		  Conncetion();
		  ResultSet rs= null;
		  boolean bol=false;
	    	try {
	    		//TODO：d、连接数据库，执行SQl语句
	        	Statement pstm=conn.createStatement();//
	        	rs= pstm.executeQuery(sql);	
	        	while(rs.next()){
	        		bol = true;
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	finally {
				try {
					close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    	return bol;
	    }
	 
	  public boolean executeInsert(String...abc) {
		  Conncetion();
		  boolean bol = false;
//		  插入
		  try {
			PreparedStatement pst = conn.prepareStatement(abc[0]);
			for(int i=1;i<abc.length;i++){
				pst.setString(i, abc[i]);
			}
			bol = pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		  return bol;
	}
	  public void executeUpdate(String sql,String...user){
		  Conncetion();
		  try {
			PreparedStatement pst = conn.prepareStatement(sql);
			for (int i = 0; i < user.length; i++) {
				pst.setString(i+1, user[i]);
				System.out.println(i+1+";"+user[i]);
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
	  }
	 /**插入class*/
	  public boolean executeClassInsert(int num,String...abc) {
		  Conncetion();
		  boolean bol = false;
//		  插入
		  try {
			PreparedStatement pst = conn.prepareStatement(abc[0]);
			for(int i=1;i<abc.length;i++){
				pst.setString(i, abc[i]);
			}
			pst.setInt(abc.length, num);
			bol = pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		  return bol;
	}
	
	  /**插入course*/
	  public boolean executeCourseInsert(String sql,String CourseName,int num,int courseIs) {
		  Conncetion();
		  boolean bol = false;
//		  插入
		  try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, CourseName);
			pst.setInt(2, num);
			pst.setInt(3, courseIs);
			bol = pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		  return bol;
	}
	  public void executeDb(String sql){
		  Conncetion();
		  try {
			Statement st = conn.createStatement();
			st.execute(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  finally {
				try {
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showConfirmDialog(null, "数据库close失败");
					e.printStackTrace();
				}
			}
	  }
	  public void close() throws SQLException{
		 
			if(conn!=null){
				conn.close();
				}
	  }
	  
//	  public static void main(String[] args) throws Exception {
//		  /**
//	    	 * here is a test .
//	    	 */
//		DBOperator dbo = new DBOperator();
//		dbo.Conncetion();
//		/**test 连接查询*/
//		
//		String userName = "Ray";
//		String userPass = "123194";
////		String sql = "select * from yhxxb where userName='"+userName+"'"; 
//		String sql = "select * from yhxxb";
//		  ResultSet rs= null;
//	    	try {
//	    		//TODO：d、连接数据库，执行SQl语句
//	        	Statement stm=conn.createStatement();
//	        	rs= stm.executeQuery(sql);	
//			} catch (Exception e) {
//				e.printStackTrace();
//			} while(rs.next()){
//        		System.out.println(rs.getString(1)+";"+rs.getString("userName"));
////        		System.out.println("-------1-----");
//        	}
//			System.out.println("------2------");
		  
//		boolean bol = dbo.loginQuery(sql, userID, userPass);
//		while(bol){
//			System.out.println("true");
//		}
		/**test插入*/
//		String sql="insert into yhxxb values(?,?,?,?,?);";
//		String userID = "123194";
//		String userName = "Ray";
//		String userType ="admin";
//		String Pass = "123194";
//		String Tel="13888888888";
//		dbo.executeInsert(sql,userID,userName,userType,Pass,Tel);
//		ResultSet rs = dbo.query("select * from yhxxb where  userID='"+userID+"'");
//		while(rs.next()){
//			System.out.println(rs.getInt(1)+";"+rs.getString("userID")+";"+rs.getString(2));
//		}
		/**test 更改*/
//		String userID= "122193";
//		String userName = "Leo";
//		String sql = "update yhxxb set userName=? where userID like ?";
//		dbo.executeUpdate(sql, userName,userID);
//		ResultSet rs= dbo.query("select * from yhxxb");
//		while(rs.next()){
//			System.out.println(rs.getInt(1)+";"+rs.getString("userID")+";"+rs.getString(2));
//		}
		
//	}
}
