package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.tedu.entity.Cost;
import cn.tedu.util.DBUtil;

@Repository
public class JdbcCostDao implements CostDao{

	@Autowired
	private DataSource dataSource;
	
	public List<Cost> findAll() {
		String sql = "select * from cost";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(
						"关闭连接失败", e);
			}
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

}
