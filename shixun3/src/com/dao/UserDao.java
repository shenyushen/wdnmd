package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDao {
	
	public ResultSet queryDate(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	public int updateDate(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(sql);
		if (rs > 0) return 1;
		else return 0;
	}
	public int deleteDate(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(sql);
		if (rs > 0) return 1;
		else return 0;
	}
	public int addData(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(sql);
		if (rs > 0) return 1;
		else return 0;
	}
}
