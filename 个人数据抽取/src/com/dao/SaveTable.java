package com.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

import com.dao.MySQLJdbc;
import com.dao.SuperDao;

public class SaveTable extends SuperDao{
	private static Connection conn;
	private static Statement st; 
	
	public int saveTable(String tableName,Vector columnName,Vector dataVector){
			Object[] columnN = columnName.toArray(); 
		try { 
			conn = MySQLJdbc.getConnection();
			String sql1 = new String(); 
			sql1 += columnN[0];
			sql1 += " varchar(40)";
			for(int i = 1;i < columnName.size();i++) {
				sql1 += ",";
				sql1 += columnN[i];
				sql1 += " varchar(80)"; 
			} 
			final String sql = "create table "+tableName+"("+sql1+");";	//新建指定名称的表 
			st = conn.createStatement();
			st.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			} 
			Vector<String> data = new Vector<String>();
			String sql2 = new String();
			System.out.println(dataVector.size());
			for(int i = 0;i < dataVector.size();i++) { 
				data = (Vector<String>) dataVector.elementAt(i);
				Object[] dataN = data.toArray();
				sql2 = "insert into "+tableName+" values(";
				for(int j = 0;j < dataN.length-1;j++) {
					sql2 += "\"";
					sql2 += dataN[j];
					sql2 += "\",";
				}
				sql2 +="\""+dataN[dataN.length-1]+"\")";
				System.out.println(i+" "+sql2);
				try {
					conn = MySQLJdbc.getConnection(); 
					st = conn.createStatement();
					int a = st.executeUpdate(sql2); 
					if (a == 0) System.out.println("insert failed");
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		return 1;
	}
}
