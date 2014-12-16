package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLJdbc {
	private static final String  drivers = "com.mysql.jdbc.Driver";
	private static final String username ="root";
	private static final String password ="ymzmdx";
	private static final String url = "jdbc:mysql://localhost:3306/bookdb"; //连接数据库
	
	public static Connection getConnection(){
		 try
		   {
			 Class.forName(drivers);  
			 return DriverManager.getConnection(url, username, password);
		   }
		   catch(Exception ex)
		   {
			   ex.printStackTrace(); 
			   return null;
		   }
		}
		
		public static void main(String[] args)
		{ 
			if(MySQLJdbc.getConnection()!=null)
			{
				System.out.println("连接成功！");
			}
			else
			{
				System.out.println("连接失败！");
			}
		}
} 
