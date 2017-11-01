package com.tarena.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.Cost;
import com.tarena.util.DBUtil;

public class CostDaoImpl implements CostDao{
	
	public List<Cost> findAll(){
		Connection conn =DBUtil.getConnection();
		String sql="select * from cost_yf order by cost_id";
		try{
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()){
				Cost c = createCost(rs);
				list.add(c);
			}
			return list;
		}catch (SQLException e){
			throw new RuntimeException("²éÑ¯×Ê·ÑÊ§°Ü",e);
		}finally {
			DBUtil.close(conn);
		}
	}

	
	private Cost createCost(ResultSet rs) throws SQLException{
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
	
	public static void main(String[] args) {
		CostDao dao = new CostDaoImpl();
		List<Cost> list = dao.findAll();
		for(Cost c : list){
			System.out.println(c.getCostId()+","+c.getName());
		}
	}

}
