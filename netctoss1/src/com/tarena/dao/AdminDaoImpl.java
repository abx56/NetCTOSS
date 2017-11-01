package com.tarena.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tarena.entity.Admin;
import com.tarena.util.DBUtil;

public class AdminDaoImpl implements AdminDao{
	
	public Admin findByCode(String code){
		if(code==null || code.equals("")){
			return null;
		}
		Connection conn = DBUtil.getConnection();
		String sql = "select * from admin_info_yf where admin_code=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Admin a = new Admin();
				a.setAdminId(rs.getInt("admin_id"));
				a.setAdminCode(rs.getString("admin_code"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("name"));
				a.setTelephone(rs.getString("telephone"));
				a.setEmail(rs.getString("email"));
				a.setEnrolldate(rs.getTimestamp("enrolldate"));
				return a;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException("查询管理员失败.",e);
		}finally{
			DBUtil.close(conn);
		}
		
	}
	
	public static void main(String[] args) {
		AdminDao dao = new AdminDaoImpl();
		Admin a = dao.findByCode("admin");
		System.out.println(a.getAdminId()+","+a.getName());
	}
}
