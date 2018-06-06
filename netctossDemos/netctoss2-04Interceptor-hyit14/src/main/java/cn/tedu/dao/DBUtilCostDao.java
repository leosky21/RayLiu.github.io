package cn.tedu.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Cost;
import cn.tedu.util.DBUtil;

/**
 * 资费表的数据访问对象
 */
public class DBUtilCostDao implements Serializable{

	public List<Cost> findAll() {
		String sql = "select * from cost";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()) {
				Cost c = createCost(rs);
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"查询资费失败", e );
		} finally {
			DBUtil.close(conn);
		}
	}

	//将结果集中一行记录，转换为一个Cost对象
	private Cost createCost(ResultSet rs) throws SQLException {
		Cost c = new Cost();
		c.setCostId(rs.getInt("cost_id"));
		c.setName(rs.getString("name"));
		c.setBaseDuration(rs.getInt("base_duration"));
		c.setBaseCost(rs.getDouble("base_cost"));
		c.setUnitCost(rs.getDouble("unit_cost"));
		c.setStatus(rs.getString("status"));
		c.setDescr(rs.getString("descr"));
		c.setCreatime(rs.getTimestamp("creatime"));
		c.setStartime(rs.getTimestamp("startime"));
		c.setCostType(rs.getString("cost_type"));
		return c;
	}

	public void save(Cost c) {
		String sql = "insert into cost values("
			+ "cost_seq.nextval,"
			+ "?,?,?,?,'1',?,sysdate,null,?)";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			pst.setString(1, c.getName());
			
			//pst.setInt(2, c.getBaseDuration());
			//第二个参数是int类型，但基本时长可能为null
			//	即：代码运行中，可能会出现空指针异常
			//	可使用pst.setObject(index, Obj)代替
			pst.setObject(2, c.getBaseDuration());
			pst.setObject(3, c.getBaseCost());
			pst.setObject(4, c.getUnitCost());
			pst.setString(5, c.getDescr());
			pst.setString(6, c.getCostType());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"新增保存资费失败",e);
		} finally {
			DBUtil.close(conn);
		}
	}

	public Cost findById(int id) {
		String sql = "select * from cost where cost_id=?";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			if( rs.next() ) {
				// rs结果集中一条记录 ==> Cost对象
				Cost c = createCost(rs);
				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"查询资费信息失败", e);
		} finally {
			DBUtil.close(conn);
		}
		
		return null;
	}

	public void update(Cost c) {
		String sql = "update cost set "
				+ "		name=?,"
				+ "		base_duration=?,"
				+ "		base_cost=?,"
				+ "		unit_cost=?,"
				+ "		descr=?,"
				+ "		cost_type=? "
				+ "	where cost_id=?";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			
			pst.setString(1, c.getName());
			pst.setObject(2, c.getBaseDuration());
			pst.setObject(3, c.getBaseCost());
			pst.setObject(4, c.getUnitCost());
			pst.setString(5, c.getDescr());
			pst.setString(6, c.getCostType());
			pst.setObject(7, c.getCostId());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"修改资费信息失败", e);
		} finally {
			DBUtil.close(conn);
		}
	}
	
	//算出Cost资费表中共多少条记录
	public int countRows() {
		String sql = "select count(*) from cost";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pst = 
					conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if( rs.next() ) {
				int rows = rs.getInt(1);
				return rows;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"查询总行数失败", e);
		} finally {
			DBUtil.close(conn);
		}
		
		return 0;
	}
	
	//查询出指定分页中记录行
	//参数：page第几页，size每页的记录条数
	public List<Cost> findByPage(int page, int size){
		String sql = 
				"select * from( " +
				"  select rownum rn, c.* " + 
				"  from( " +
				"    select * from cost " + 
				"    order by cost_id) c " +
				") where rn between ? and ?";
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pst = 
				conn.prepareStatement(sql);
			int begin = (page-1)*size+1; //6
			int end = page*size; //10
			pst.setInt(1, begin);
			pst.setInt(2, end);
			ResultSet rs = pst.executeQuery();
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()) {
				Cost c = createCost(rs);
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"分页查询资费信息失败", e);
		} finally {
			DBUtil.close(conn);
		}
	}
	/*
	select * from(
	  select rownum rn, c.* 
	  from(
	    select * from cost 
	    order by cost_id) c
	) where rn between 6 and 10;
	*/
}








