package com.tarena.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String driver;
	private static String user;
	private static String url;
	private static String pwd;
	;
	static{
		Properties p = new Properties();
		try{
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			url = p.getProperty("url");
			driver = p.getProperty("driver");
			user = p.getProperty("user");
			pwd = p.getProperty("pwd");
			Class.forName(driver);
		}catch (Exception e){
			throw new RuntimeException("读取链接参数失败",e);
		}
	}

	public static Connection getConnection(){
		try{
			return DriverManager.getConnection(url, user, pwd);
			
		}catch (SQLException e){
			throw new RuntimeException(
					"创建连接失败",e);
		}
	}
	public static void close(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch (SQLException e){
				throw new RuntimeException("关闭链接失败,e");
			}
		}
	}
	
		public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
		DBUtil.close(conn);
	}
}
