package test;
import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private static final String  drivers = "com.mysql.jdbc.Driver";
	private static final String username ="root";
	private static final String password ="1993915";
	private static final String url = "jdbc:mysql://localhost:3306/ExtractDB"; //连接数据库
	
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
} 
