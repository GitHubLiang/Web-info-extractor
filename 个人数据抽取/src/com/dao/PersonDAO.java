/**
 * 执行关于人员的数据库操作（添加人员、删除人员）
 */
package com.dao; 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;

import com.dao.MySQLJdbc;
import com.dao.SuperDao;
import com.entity.*;   

public class PersonDAO extends SuperDao{ 
		private static Connection conn;
		private static Statement st; 
		private static String pass = new String();
		private static String user = new String();
		private static String is = new String();
		
		public Person getPerson(String userNamestr,String passWord) {
			Person p = null;
			String sql = new String();
			sql = "select * from person where userName=\'"+userNamestr+"\'";
			try {
				conn = MySQLJdbc.getConnection();
				System.out.println(sql);
				st = conn.createStatement(); 
				rs = st.executeQuery(sql);
				while (rs.next()) {    
					user = rs.getString("userName");
					pass = rs.getString("passWord");
					is = rs.getString("isManage");
					System.out.println(user+"---"+passWord);
					if(user.equals(userNamestr) && pass.equals(passWord)){
						p = new Person();
						p.setuserName(user);
						p.setpassWord(pass);
						p.setisManage(is); 
						return p;
					}
				}
				return null; 
			}catch(Exception e){
			e.printStackTrace();
			return null;
			}
		}
		/**
		 * 删除人员信息
		 * @param userName
		 * @return
		 * @throws IOException
		 */
		public String deletePerson(String userName) throws IOException {  
			String sql = new String();
			sql = "delete* from person where userName="+userName+";";
			try {
				conn = MySQLJdbc.getConnection();  
				st = conn.createStatement(); 
				st.executeUpdate(sql);
				}catch(Exception e){
					e.printStackTrace();
					return "erro";
				}
			return "success";
		}

		/**
		 * 更新人员信息	 
		 */
		public String updatePerson(Person p) throws IOException { 
			String sql = "update person set userName=?,passWord=?,isManage=?";
			PreparedStatement t = null;
	        try {
	            conn = MySQLJdbc.getConnection();
	            t = conn.prepareStatement(sql);
	            t.setString(1,p.getuserName());
	            t.setString(2,p.getpassWord());
	            t.setString(3,p.getisManage()); 
	            t.executeUpdate(); 
	        } catch (Exception e) {
				e.printStackTrace();
				return "erro";
	        }  
	        return "success";
	    }
		/**
		 * 添加人员信息	 
		 */
		public String addPerson(Person p) throws IOException {
			String sql = new String();
			sql = "insert into person values("+p.getuserName()+","+p.getpassWord()+","+p.getisManage()+");";
			try {
				conn = MySQLJdbc.getConnection();
				st = conn.createStatement();
				st.executeUpdate(sql);
				}catch(Exception e) {
					e.printStackTrace();
					return "erro";
				}
			return "success";
		}
		/**
	     * 查询所有人员信息
	     * 
	     * @return 人员集合
	     */
	    public List<Person> listPerson() {
	        String sql = "select * from person";
	        List<Person> list = new ArrayList<Person>(); 
	        PreparedStatement ps = null;
	        try {
	            conn = MySQLJdbc.getConnection();
	            ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Person per = new Person();
	                per.setuserName(rs.getString("userName")); 
	                per.setpassWord(rs.getString("password")); 
	                per.setisManage(rs.getString("isManage"));
	                list.add(per);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        return list;
	    }
				 
}