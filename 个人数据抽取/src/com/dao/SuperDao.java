package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//所有业务逻辑的父类
public class SuperDao {

	protected static Connection conn = null;
	protected static PreparedStatement stmt = null;
	protected static ResultSet rs = null;

	protected void destoryResource() {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
